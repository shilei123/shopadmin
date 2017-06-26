package framework.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.lob.SerializableClob;
import org.hibernate.mapping.RootClass;
import org.hibernate.transform.ResultTransformer;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import common.Logger;
import framework.bean.UserMsg;
import framework.helper.RequestHelper;
import framework.config.SysDict;
import framework.util.DateUtils;
import framework.util.ToUtils;

@SuppressWarnings({"rawtypes","unchecked"})
@Component("dbUtil")
public class DBUtil {
	public final static MapResultTransformer RESULT_TRANSFORMER_HASHMAP = new MapResultTransformer();
	public final static ListOrderedMapResultTransformer RESULT_TRANSFORMER_LIST_ORDER_MAP = new ListOrderedMapResultTransformer();
	public final static ArrayResultTransformer RESULT_TRANSFORMER_ARRAY = new ArrayResultTransformer();
	public final static ListResultTransformer RESULT_TRANSFORMER_LIST = new ListResultTransformer();
	public final static JsonResultTransformer RESULT_TRANSFORMER_JSON = new JsonResultTransformer();
	
	public final static ListOrderedMap entityMap = new ListOrderedMap();
	
	public final static Logger log = Logger.getLogger(DBUtil.class);
	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	private ResultTransformer resultTransformer;
	
	/**
	 * 获取映射的实体对象Class
	 * @param name
	 * @return
	 */
	public static Class getEntity(Object name){
		return (Class)getEntitys().get(name);
	}
	
	/**
	 * 获取映射的实体对象集合
	 */
	public static ListOrderedMap getEntitys(){
		if(entityMap.size()==0){
			Configuration config = HibernateSessionFactory.getConfiguration();
			Iterator itor = config.getClassMappings();
			while(itor.hasNext()){
				RootClass rc = (RootClass)itor.next();
				try {
					entityMap.put(rc.getNodeName(),Class.forName(rc.getClassName()));
				} catch (ClassNotFoundException e) {
					log.error("",e);
				}
			}
		}
		return entityMap;
	}

	/**
	 * 获取单例
	 * @param c
	 * @param id
	 * @return
	 */
	public Object get(Class c, Serializable id){
		return this.hibernateTemplate.get(c, id);
	}
	
	/**
	 * 获取序列
	 * @param seqName 序列名称
	 * @return
	 */
	public Integer getSequece(final String seqName){
		Integer obj = this.hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery("select "+seqName.toUpperCase()+".nextval from dual");
				return ((BigDecimal)query.list().get(0)).intValue();
           }
		});
		return obj;
	}
	
	/**
	 * 获取多个序列
	 * @param seqName 序列名称
	 * @param count 需要的序列个数
	 * @return
	 */
	public Integer[] getSequeces(String seqName, int count){
		Integer[] seqs = new Integer[count];
		for (int i = 0; i < seqs.length; i++) {
			seqs[i] = getSequece(seqName);
		}
		return seqs;
	}
	
	/**
	 * 查询Pojo列表
	 * @param clas Pojo的class
	 * @param where HQL查询语句
	 * @param params 与HQL所映射的参数
	 * @param orderby 排序（不需要添加 “order by ”的字样，为空则不会添加）
	 * @return ArrayList<Pojo>
	 */
	public List queryByPojo(Class clas , String where , Map params, String orderby){
       StringBuffer temp = new StringBuffer("from ").append(clas.getName()).append(" as tmp where 1=1");
        temp.append(where==null?"":where);
        temp.append(orderby==null?"":" order by "+orderby);
        return queryByHql(temp.toString(), params);
    }
	
	/**
	 * 分页查询
	 * @param clas
	 * @param where
	 * @param params
	 * @param orderby
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List queryByPojo(Class clas , String where , Map params, String orderby, int pageIndex , int pageSize){
		StringBuffer temp = new StringBuffer("from ").append(clas.getName()).append(" as tmp where 1=1");
        temp.append(where==null?"":where);
        temp.append(orderby==null?"":" order by "+orderby);
        return queryByHql(temp.toString(), params, pageIndex, pageSize);
	}
	
	/**
	 * 查询pojo行数
	 * @param clas
	 * @param where
	 * @param params
	 * @return
	 */
	public int queryByPojoCount(Class clas , String where , Map params){
		StringBuffer temp = new StringBuffer("from ").append(clas.getName()).append(" as tmp where 1=1");
        temp.append(where==null?"":where);
        return queryByHqlCount(temp.toString(), params);
	}
	
	/**
	 * 查询hql
	 * @param hql
	 * @param params
	 * @return
	 */
	public List queryByHql(String hql, Map params){
        return queryByHql(hql, params, 0, 0);
	}
	
	/**
	 * 查询hql
	 * @param hql
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List queryByHql(final String hql, final Map params, final int pageIndex , final int pageSize) {
		List list = hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
		        if(params!=null && params.size()>0){
		            Iterator iter = params.keySet().iterator();
		            while(iter.hasNext()){
		                String key = iter.next().toString();
		                Object value = params.get(key);
		                query.setParameter(key, value);
		            }
		        }
		        if(pageSize>0) {
		        	int firstResult = pageIndex * pageSize - pageSize;
		        	query.setFirstResult(firstResult);
		        	query.setMaxResults(pageSize);
		        }
		        query.setResultTransformer(resultTransformer==null?RESULT_TRANSFORMER_HASHMAP:resultTransformer);
		        return query.list();
           }
		});
		return list;
	}
	
	/**
	 * 根据别名执行查询
	 * @param hql
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List queryByName(final String name, final Map params, final int pageIndex , final int pageSize){
		List list = hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.getNamedQuery(name);
				if(params != null && params.size() > 0) {
					Iterator iter = params.keySet().iterator();
					while(iter.hasNext()){
						String key = iter.next().toString();
						Object value = params.get(key);
						query.setParameter(key, value);
					}
				}
				if(pageSize > 0) {
					int firstResult = pageIndex * pageSize - pageSize;
					query.setFirstResult(firstResult);
					query.setMaxResults(pageSize);
				}
				query.setResultTransformer(resultTransformer==null?RESULT_TRANSFORMER_HASHMAP:resultTransformer);
				return query.list();
           }
		});
		return list;
	}
	
	/**
	 * 查询hql数据行数
	 * @param hql
	 * @param params
	 * @return
	 */
	public int queryByHqlCount(final String hql, final Map params) {
		Integer obj = hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
		        if(params != null && params.size() > 0) {
		            Iterator iter = params.keySet().iterator();
		            while(iter.hasNext()) {
		                String key = iter.next().toString();
		                Object value = params.get(key);
		                query.setParameter(key, value);
		            }
		        }
		        Object result = query.uniqueResult(); 
		        return Integer.parseInt(result.toString());
           }
		});
		return obj;
	}
	
	/**
	 * 查询Pojo列表
	 * @param clas Pojo的class
	 * @return  ArrayList<Pojo>
	 */
	public List queryByPojo(Class clas) {
		return queryByPojo(clas, null, null);
	}
	
	/**
	 * 查询Pojo列表
	 * @param clas Pojo的class
	 * @param params 与Pojo的属性名称相映射的参数（参数必须与属性类型一致）
	 * @return ArrayList<Pojo>
	 */
	public List queryByPojo(Class clas , Map params){
		return queryByPojo(clas, params, null);
	}
	
	/**
	 * 查询Pojo列表
	 * @param clas Pojo的class
	 * @param params 与Pojo的属性名称相映射的参数（参数必须与属性类型一致）
	 * @param orderby 排序（不需要添加 “order by ”的字样，为空则不会添加）
	 * @return ArrayList<Pojo>
	 */
	public List queryByPojo(final Class clas , final Map params, final String orderby){
		List list = hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				StringBuffer temp = new StringBuffer(" from ").append(clas.getName()).append(" as tmp where 1=1 ");
				
				List paramList = buildParams(temp, params);
				buildOrderby(temp, orderby);//排序参数
				
				//创建查询，并执行
				Query query = session.createQuery(temp.toString());
				for (int i = 0; i < paramList.size(); i++) {
					query.setParameter(i, paramList.get(i));
				}
				
				return query.list();
           }
		});
		return list;
	}
	
	/**
	 * 构建参数，拼接到sql语句，同时返回参数集合
	 * @param sql 语句
	 * @param params 参数
	 * @return
	 */
	private static List buildParams(StringBuffer sql, Map params) {
		List paramList = new ArrayList();
		if(params != null && params.size() > 0) {
			Iterator iter = params.keySet().iterator();
			Object key,value;
			while(iter.hasNext()){
				key = iter.next();
				value = params.get(key);
				sql.append(" and tmp.");
				sql.append(key);
				if(value!=null){
					sql.append("=? ");
					paramList.add(value);
				}
			}
		}
		return paramList;
	}
	
	/**
	 * 为sql语句子追加or derby
	 * @param sql
	 * @param orderby
	 */
	private static void buildOrderby(StringBuffer sql, String orderby){
		if(orderby!=null && !orderby.equals("")){
			sql.append(" order by ");
			sql.append(orderby);
		}
	}
	
	/**
	 * 执行sql语句
	 * @param sql 语句
	 * @param params 参数（按参数先后循序为sql语句的替代符赋值）
	 * @return ArrayList<Map>
	 */
	public List queryBySQL(String sql, Object... params) {
		List plist = new ArrayList(params.length);
		if(params!=null && params.length>0){
			for (int i = 0; i < params.length; i++) {
				plist.add(params[i]);
			}
		}
		return queryBySQL(sql, plist);
	}
	
	/**
	 * 执行HQL语句
	 * @param hql 语句
	 * @param params 参数（按参数先后循序为sql语句的替代符赋值）
	 * @return ArrayList<Map>
	 */
	public List queryByHql(String hql, Object... params) {
		List plist = new ArrayList(params.length);
		if(params!=null && params.length>0){
			for (int i = 0; i < params.length; i++) {
				plist.add(params[i]);
			}
		}
		return queryByHql(hql, plist,0,0);
	}
	
	/**
	 * 查询总行数(自动替换Sql语句，暂不能支持复杂的语句，如列有子查询的语句，或其他复杂语句)
	 * @param sql 语句
	 * @param params 参数
	 * @return ArrayList<Map>
	 */
	public int queryCountBySQL(String sql, List params) {
		sql = sql.toUpperCase();
		int startIndex = sql.indexOf("FROM");
		sql = "SELECT COUNT(1) ROW_COUNT "+sql.subSequence(startIndex, sql.length());
		List list = queryBySQL(sql, params, 0, 0);
		return Integer.parseInt(((Map)list.get(0)).get("rowCount").toString());
	}
	
	/**
	 * 查询总行数(自动替换Sql语句，暂不能支持复杂的语句，如列有子查询的语句，或其他复杂语句)
	 * @param sql 语句
	 * @param params 参数
	 * @return ArrayList<Map>
	 */
	public int queryCountByHql(String hql, List params) {
		int startIndex = hql.indexOf("from");
		hql = " select count(*) as rowcount "+hql.subSequence(startIndex, hql.length());
		List list = this.queryByHql(hql, params, 0, 0);
		return Integer.parseInt(((Map)list.get(0)).get("rowcount").toString());
	}
	
	/**
	 * 执行sql语句
	 * @param sql 语句 
	 * @param params 参数
	 * @return ArrayList<Map>
	 */
	public List queryBySQL(String sql, List params){
		return queryBySQL(sql, params, 0, 0);
	}
	
	/**
	 * 执行sql语句，并分页查询
	 * @param sql 语句
	 * @param params 参数
	 * @param firstResult 起始行数
	 * @param maxResults 最大行数
	 * @return ArrayList<Map>
	 */
	public List queryBySQL(final String sql, final List params , final int startRowIndex, final int maxResults){
		List list = hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
        	   Query query = session.createSQLQuery(sql);
        	   if(params!=null && params.size()>0){
	       			for (int i = 0; i < params.size(); i++) {
	       				query.setParameter(i, params.get(i));
	       			}
	       		}
	       		if(maxResults>0){
	       			query.setFirstResult(startRowIndex);
	       			query.setMaxResults(maxResults);
	       		}
	       		query.setResultTransformer(resultTransformer==null?RESULT_TRANSFORMER_HASHMAP:resultTransformer);
	       		return query.list();
           }
		});
		return list;
	}
	
	/**
	 * 执行hql语句，并分页查询
	 * @param sql 语句
	 * @param params 参数
	 * @param firstResult 起始行数
	 * @param maxResults 最大行数
	 * @return ArrayList<Map>
	 */
	public List queryByHql(final String hql, final List params , final int startRowIndex, final int maxResults){
		List list = hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				if(params!=null && params.size()>0){
					for (int i = 0; i < params.size(); i++) {
						query.setParameter(i, params.get(i));
					}
				}
				if(maxResults>0){
					query.setFirstResult(startRowIndex);
					query.setMaxResults(maxResults);
				}
				//query.setResultTransformer(resultTransformer==null?RESULT_TRANSFORMER_HASHMAP:resultTransformer);
				return query.list();
           }
		});
		return list;
	}
	
	/**
	 * 查询sql语句 ，按循map填充参数， key作为列名
	 * @param sql 
	 * @param params
	 * @return ArrayList<Map>
	 */
	public List queryBySQL(String sql, Map params){
		StringBuffer temp = new StringBuffer(sql);
		List paramList = new ArrayList();
		if(params!=null && params.size()>0){
			Iterator iter = params.keySet().iterator();
			Object key,value;
			while(iter.hasNext()){
				key = iter.next();
				value = params.get(key);
				if(value!=null){
					temp.append(" ");
					temp.append(key);
					temp.append("=? ");
					paramList.add(value);
				}
			}
		}
		return this.queryBySQL(temp.toString(), paramList.toArray());
	}
	
	/**
	 * 执行SQL语句，按循map填充参数， key作为列名
	 * @param sql
	 * @param params
	 * @return 影响行数
	 */
	public int executeSQL(String sql, Map params){
		StringBuffer temp = new StringBuffer(sql);
		List paramList = new ArrayList();
		if(params!=null && params.size()>0){
			Iterator iter = params.keySet().iterator();
			Object key,value;
			while(iter.hasNext()){
				key = iter.next();
				value = params.get(key);
				if(value!=null){
					temp.append(" ");
					temp.append(key);
					temp.append("=? ");
					paramList.add(value);
				}
			}
		}
		return this.executeSQL(temp.toString(), paramList.toArray());
	}
	
	/**
	 * 执行SQL语句，按循序填充参数
	 * @param sql sql语句
	 * @param params 参数
	 * @return 影响行数
	 */
	public int executeSQL(final String sql, final Object... params) {
		int i = this.hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql);
				int paramIndex = 0;
				if(params!=null && params.length>0){
					for (int i = 0; i < params.length; i++) {
						if(params[i]!=null){
							query.setParameter(paramIndex++, params[i]);
						}
					}
				}
				return query.executeUpdate();
			}
		});
		return i;
	}
	
	/**
	 * 执行hql语句，按循序填充参数
	 * @param hql hql语句
	 * @param params 参数
	 * @return 影响行数
	 */
	public int executeHql(final String hql, final Object... params) {
		int i = this.hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				int paramIndex = 0;
				if(params!=null && params.length>0){
					for (int i = 0; i < params.length; i++) {
						if(params[i]!=null){
							query.setParameter(paramIndex++, params[i]);
						}
					}
				}
				return query.executeUpdate();
			}
		});
		return i;
	}
	
	/**
	 * 执行sql语句
	 * @param sql sql语句
	 * @param params 参数集合
	 * @return 影响行数
	 */
	public int executeSQL(String sql, List params) {
		if(params==null) {
			return executeSQL(sql);
		} else {
			return executeSQL(sql, params.toArray());
		}
	}
	
	/**
	 * 更新一个pojo
	 * @param pojo对象
	 */
	public void insert(List pojos) {
		for(Object o : pojos){
			insert(o);
		}
	}
	
	/**
	 * 插入一个pojo（当会话未自动开启事务，将自动开启事务）
	 * @param pojo对象
	 */
	public Object insert(Object pojo){
		setDefaultValueByInsert(pojo);//自动输入录入人员 录入时间
		return this.hibernateTemplate.save(pojo);
	}
	
	
	//pojo方法对象缓存Map
	private final static Map<String,Method> pojoMethodMap = new HashMap();
	//没有这个方法缓存判断使用属性
	private static Method noMethod = null;
	static {
		try {
			noMethod = DBUtil.class.getDeclaredMethod("noMethod");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 作为pojo方法缓存不存在这个方法的占位方法，没有实际意义，作判断用
	 */
	public void noMethod(){}
	
	/**
	 * 设置pojo值（使用反射的方式为pojo赋值，并且缓存该pojo的所有方法）
	 * @param pojo 赋值对象
	 * @param methodName 赋值方法 
	 * @param parentClass 参数类型
	 * @param value 赋值
	 */
	public static void setPojoValue(Object pojo, String methodName, Class parentClass, Object value){
		String getkey = pojo.getClass().getName()+"."+methodName+":"+parentClass.getName()+":get";
		try {
			if(pojoMethodMap.get(getkey)==null){//缓存
				try{
					pojoMethodMap.put(getkey, pojo.getClass().getDeclaredMethod("get"+methodName));
				}catch(Exception e){
					pojoMethodMap.put(getkey, noMethod);
				}
			}
			Method method = pojoMethodMap.get(getkey);
			if(method!=null && !method.equals(noMethod)){
				Object result = method.invoke(pojo);
				if(result==null){
					String setkey = pojo.getClass().getName()+"."+methodName+":"+parentClass.getName()+":set";
					if(pojoMethodMap.get(setkey)==null){//缓存
						try{
							pojoMethodMap.put(setkey, pojo.getClass().getDeclaredMethod("set"+methodName, parentClass));
						}catch(Exception e){
							pojoMethodMap.put(setkey, noMethod);
						}
					}
					method = pojoMethodMap.get(setkey);
					if(method!=null && !method.equals(noMethod)){
						method.invoke(pojo, value);
					}
				}
			}
		} catch (Exception e) {
			log.error("异常",e);
		}
	}
	
	/**
	 * 插入操作时默认值赋值（数据库列:create_time,flag,create_user_id）
	 * @param pojo
	 */
	public static void setDefaultValueByInsert(Object pojo){
		setPojoValue(pojo, "CreateTime", java.sql.Timestamp.class, DateUtils.getTimestamp());
		setPojoValue(pojo, "Flag", String.class, SysDict.FLAG_ACT);
		UserMsg user = (UserMsg)RequestHelper.getSession().getAttribute("user");
		if(user!=null){
			setPojoValue(pojo, "CreateUserId", String.class, user.getUId());
		}
	}
	
	/**
	 * 修改操作时默认值赋值（数据库列:update_time）
	 * @param pojo
	 */
	public static void setDefaultValueByUpdate(Object pojo){
		setPojoValue(pojo, "UpdateTime", java.sql.Timestamp.class, DateUtils.getTimestamp());
	}
	
	/**
	 * 保存或更新一个pojo
	 * @param pojo对象
	 */
	public void saveOrUpdate(Object pojo){
		setDefaultValueByInsert(pojo);//自动输入录入人员 录入时间
		setDefaultValueByUpdate(pojo);//自动更新修改时间
		this.hibernateTemplate.saveOrUpdate(pojo);
	}
	
	/**
	 * 保存或更新一个pojo
	 * @param pojo对象
	 */
	public void saveOrUpdate(List list){
		for(Object obj : list){
			saveOrUpdate(obj);
		}
	}
	
	/**
	 * 更新一个pojo
	 * @param pojo对象
	 */
	public void update(List pojos){
		for(Object o : pojos){
			update(o);
		}
	}
	
	/**
	 * 更新一个pojo
	 * @param pojo对象
	 */
	public void update(Object pojo){
		setDefaultValueByUpdate(pojo);//自动更新修改时间
		this.hibernateTemplate.update(pojo);
	}
	
	/**
	 * 删除一个Pojo
	 * @param pojo对象
	 */
	public void delete(List pojos){
		for(Object o : pojos){
			delete(o);
		}
	}
	
	/**
	 * 删除一个Pojo
	 * @param pojo对象
	 */
	public void delete(Object pojo){
		this.hibernateTemplate.delete(pojo);
	}

	/**
	 * <p>获取在请求中存放的数据库工具类<p>
	 * @return
	 */
	public static DBUtil getInstance() {
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();  
		return (DBUtil) context.getBean("dbUtil");  
	}

	/**
	 * 获取当前使用的返回对象解析
	 * @return
	 */
	public ResultTransformer getResultTransformer() {
		return resultTransformer;
	}

	/**
	 * 设置当前使用的返回对象解析（默认HashMap，DBUtil有三种已有方法可选择，也可以自定义）
	 * @param resultTransformer
	 */
	public void setResultTransformer(ResultTransformer resultTransformer) {
		this.resultTransformer = resultTransformer;
	}
	
	/**
	 * 设置当前使用的返回对象解析（默认）
	 */
	public void setResultTransformer() {
		this.resultTransformer = RESULT_TRANSFORMER_HASHMAP;
	}
	
	/**
	 * 设置当前使用的返回对象解析（默认HashMap，DBUtil有三种已有方法可选择，也可以自定义）
	 * @param resultTransformerName class路径
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public void setResultTransformer(String resultTransformerName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		this.resultTransformer = (ResultTransformer)Class.forName(resultTransformerName).newInstance();
	}
	
	/**
	 *  拼接日期查询  >=beginDate, <endDate+1
	 * @param sql
	 * @param params
	 * @param queryParams
	 */
	public static void sqlAddDate(StringBuffer sql, List params, Map queryParams){
		if(queryParams==null){return ;}
		sqlAddDate(sql, params, "create_time", queryParams.get("beginDate"), queryParams.get("endDate"));
	}
	
	/**
	 * 拼接日期查询  >=beginDate, <endDate+1
	 * @param sql
	 * @param params
	 * @param sqlStr
	 * @param queryParams 开始日期：beginDate， 结束日期：endDate
	 */
	public static void sqlAddDate(StringBuffer sql, List params, String sqlStr, Map queryParams){
		if(queryParams==null){return ;}
		sqlAddDate(sql, params, sqlStr, queryParams.get("beginDate"), queryParams.get("endDate"));
	}
	
	/**
	 * 拼接日期查询  >=beginDate, <endDate+1
	 * @param sql
	 * @param params
	 * @param sqlStr
	 * @param beginDate
	 * @param endDate
	 */
	public static void sqlAddDate(StringBuffer sql, List params, String sqlStr, Object beginDate, Object endDate){
		if(!"".equals(beginDate)){
			sqlAddByNull(sql, params, sqlStr, beginDate, beginDate, ">=", "and", "to_date(?,'yyyy-MM-dd')");
		}
		if(!"".equals(endDate)){
			sqlAddByNull(sql, params, sqlStr, endDate, endDate, "<", "and", "to_date(?,'yyyy-MM-dd')+1");
		}
	}
	
	public static void sqlAdd(StringBuffer sql, List params, String sqlStr, Object value){
		sqlAddByNullStr(sql, params, sqlStr, value, value, "=", "and");
	}
	
	public static void sqlAddByEqualAnd(StringBuffer sql, List params, String sqlStr, Object value){
		sqlAddByNullStr(sql, params, sqlStr, value, value, "=", "and");
	}
	public static void sqlAddByLtAnd(StringBuffer sql, List params, String sqlStr, Object value){
		sqlAddByNullStr(sql, params, sqlStr, value, value, "<", "and");
	}
	public static void sqlAddByGtAnd(StringBuffer sql, List params, String sqlStr, Object value){
		sqlAddByNullStr(sql, params, sqlStr, value, value, ">", "and");
	}
	public static void sqlAddByEqualLtAnd(StringBuffer sql, List params, String sqlStr, Object value){
		sqlAddByNullStr(sql, params, sqlStr, value, value, "<=", "and");
	}
	public static void sqlAddByEqualGtAnd(StringBuffer sql, List params, String sqlStr, Object value){
		sqlAddByNullStr(sql, params, sqlStr, value, value, ">=", "and");
	}
	
	public static void sqlAddByEqualOr(StringBuffer sql, List params, String sqlStr, Object value){
		sqlAddByNullStr(sql, params, sqlStr, value, value, "=", "or");
	}
	public static void sqlAddByLtOr(StringBuffer sql, List params, String sqlStr, Object value){
		sqlAddByNullStr(sql, params, sqlStr, value, value, "<", "or");
	}
	public static void sqlAddByGtOr(StringBuffer sql, List params, String sqlStr, Object value){
		sqlAddByNullStr(sql, params, sqlStr, value, value, ">", "or");
	}
	public static void sqlAddByEqualLtOr(StringBuffer sql, List params, String sqlStr, Object value){
		sqlAddByNullStr(sql, params, sqlStr, value, value, "<=", "or");
	}
	public static void sqlAddByEqualGtOr(StringBuffer sql, List params, String sqlStr, Object value){
		sqlAddByNullStr(sql, params, sqlStr, value, value, ">=", "or");
	}
	
	public static void sqlAddByLike(StringBuffer sql, List params, String sqlStr, Object value, Object value2){
		sqlAddByNullStr(sql, params, sqlStr, value, "%"+value2+"%", "like", "and");
	}
	public static void sqlAddByLike(StringBuffer sql, List params, String sqlStr, Object value){
		sqlAddByNullStr(sql, params, sqlStr, value, "%"+value+"%", "like", "and");
	}
	public static void sqlAddByGtLike(StringBuffer sql, List params, String sqlStr, Object value){
		sqlAddByNullStr(sql, params, sqlStr, value, value+"%", "like", "and");
	}
	public static void sqlAddByLtLike(StringBuffer sql, List params, String sqlStr, Object value){
		sqlAddByNullStr(sql, params, sqlStr, value, "%"+value, "like", "and");
	}
	public static void sqlAddByInAnd(StringBuffer sql, List params, String sqlStr, Object[] array){
		sqlAddByIn(sql, params, sqlStr, array, "and");
	}
	public static void sqlAddByInOr(StringBuffer sql, List params, String sqlStr, Object[] array){
		sqlAddByIn(sql, params, sqlStr, array, "or");
	}
	
	public static void sqlAddByInAnd(StringBuffer sql, List params, String sqlStr, List array){
		sqlAddByIn(sql, params, sqlStr, array.toArray(), "and");
	}
	public static void sqlAddByInOr(StringBuffer sql, List params, String sqlStr, List array){
		sqlAddByIn(sql, params, sqlStr, array.toArray(), "or");
	}
	
	public static void sqlAddByIn(StringBuffer sql, List params, String sqlStr, Object[] array){
		sqlAddByIn(sql, params, sqlStr, array, "and");
	}
	public static void sqlAddByIn(StringBuffer sql, List params, String sqlStr, List array){
		sqlAddByIn(sql, params, sqlStr, array.toArray(), "or");
	}
	
	public static void sqlAddByIn(StringBuffer sql, List params, String sqlStr, Object[] array, String addStr){
		if(array!=null && array.length>0){
			sql.append(" ");
			sql.append(addStr);	sql.append(" ");
			sql.append(sqlStr);	sql.append(" in(");
			for(int i=0; i<array.length ;i++){
				sql.append("?");
				sql.append(i<array.length-1?",":"");
				params.add(array[i]);
			}
			sql.append(")");
		}
	}
	
	public static void sqlAddByNullStr(StringBuffer sql, List params, String sqlStr, Object value, Object value2, String addStr, String appendBj){
		if(!"".equals(value)){
			sqlAddByNull(sql, params, sqlStr, value, value2, addStr, appendBj);
		}
	}
	
	/**
	 * @param sql 被拼接的sql语句
	 * @param params 参数结合
	 * @param sqlStr 需要拼接的sql语句
	 * @param value 参数值
	 * @param value2 实际参数值
	 * @param appendBj 连接标记 如: > = < >= <= like
	 * @param addStr 添加条件 如： and or
	 */
	public static void sqlAddByNull(StringBuffer sql, List params, String sqlStr, Object value, Object value2, String addStr, String appendBj){
		if(value!=null){
			sqlAddByNull(sql, params, sqlStr, value, value2, addStr, appendBj, "?");
		}
	}
	
	/**
	 * @param sql 被拼接的sql语句
	 * @param params 参数结合
	 * @param sqlStr 需要拼接的sql语句
	 * @param value 参数值
	 * @param value2 实际参数值
	 * @param appendBj 连接标记 如: > = < >= <= like
	 * @param addStr 添加条件 如： and or
	 */
	public static void sqlAddByNull(StringBuffer sql, List params, String sqlStr, Object value, Object value2, String addStr, String appendBj, String paramStr){
		if(value!=null){
			sql.append(" ");
			sql.append(appendBj);	sql.append(" ");
			sql.append(sqlStr);	sql.append(" ");
			sql.append(addStr);	sql.append(" ");
			sql.append(paramStr);
			params.add(value2);
		}
	}
	
	/**
	 * 将结果集转化为Map
	 * @param list
	 * @param key
	 */
	public static Map<String,Map> listToMap(List<Map> list, String key){
		Map<String,Map> result = new HashMap(list.size());
		for(Map row : list){
			Object obj = row.get(key);
			result.put(obj==null?null:obj.toString(), row);
		}
		return result;
	}
	
	/**
	 * 将结果集转化为Map
	 * @param list
	 * @param key
	 */
	public static Map<String,Map> listToMap(List<Map> list, String[] keys, String partitionChar){
		Map<String,Map> result = new HashMap(list.size());
		for(Map row : list){
			StringBuffer key = new StringBuffer();
			String mark = "";
			for(String k: keys){
				key.append(k).append(mark);
				mark = partitionChar;
			}
			result.put(key.toString(), row);
		}
		return result;
	}
	
	
	public static boolean notEmpty(Object obj){
		return !empty(obj);
	}
	
	public static boolean empty(Object obj){
		return (obj==null || obj.toString().trim().equals(""));
	}
	
	
	
	/**
	 * 绑定in条件语句
	 * @param sql sql
	 * @param params 参数
	 * @param where where条件,不需要写in(?,?)这部分
	 * @param values 结果结合
	 */
	public static void bindIn(StringBuffer sql, List params, String where , Object[] values){
		if(values==null || values.length==0){
			return;
		}
		sql.append(where).append(" in (");
		String m = "";
		for(Object val : values){
			sql.append(m).append("?");
			params.add(val);
			m = ",";
		}
		sql.append(")");
	}
	
	/**
	 * 绑定条件语句,不绑定参数
	 * @param sql sql
	 * @param where where条件sql语句
	 * @param judge 判断对象
	 */
	public static void bind(StringBuffer sql, String where , Object judge){
		bind(sql, null, where , judge, null);
	}
	
	/**
	 * 绑定条件语句和参数 
	 * @param sql sql
	 * @param params 参数集合
	 * @param where where条件sql语句
	 * @param value 赋值对象
	 */
	public static void bind(StringBuffer sql, List params, String where , Object value){
		bind(sql, params, where , value, value);
	}

	
	/**
	 * 绑定条件语句和参数 
	 * @param sql sql
	 * @param params 参数集合
	 * @param where where条件sql语句
	 * @param judge 判断对象
	 * @param value 赋值对象
	 */
	public static void bind(StringBuffer sql, List params, String where , Object judge, Object value){
		if(judge!=null && !"".equals(judge)){
			sql.append(where);
			if(value!=null){
				params.add(value);
			}
		}
	}
}

/**
 * 查询返回对象解析（ArrayList）
 * @author Administrator
 *
 */
@SuppressWarnings({"rawtypes","unchecked", "serial"})
class ListResultTransformer implements ResultTransformer{
	public List transformList(List arg0) {
		return arg0;
	}
	public Object transformTuple(Object[] values, String[] keys) {
		List list = new ArrayList(values.length);
		for (int i = 0; i < values.length; i++) {
			list.add(values[i]);
		}
		return list;
	}
}

/**
 * 查询返回对象解析（Object[]）
 * @author Administrator
 *
 */
@SuppressWarnings({"rawtypes", "serial"})
class ArrayResultTransformer implements ResultTransformer{
	public List transformList(List arg0) {
		return arg0;
	}
	public Object transformTuple(Object[] values, String[] keys) {
		Object[] array = new Object[values.length];
		for (int i = 0; i < values.length; i++) {
			array[i] = values[i];
		}
		return array;
	}
}

/**
 * 查询返回对象解析（HashMap）(默认)
 * @author Administrator
 *
 */
@SuppressWarnings({"rawtypes","unchecked", "serial"})
class MapResultTransformer implements ResultTransformer{
	public final static Logger log = Logger.getLogger(MapResultTransformer.class);
	
	public List transformList(List arg0) {
		return arg0;
	}
	public Object transformTuple(Object[] values, String[] keys) {
		HashMap map = new HashMap(values.length);
		for (int i = 0; i < keys.length; i++) {
			if(values[i]!=null && SerializableClob.class.equals(values[i].getClass())){
				SerializableClob s = (SerializableClob)values[i];
				BufferedReader br = null;
				try {
					/*int len = s.getAsciiStream().available();
					byte[] tmp = new byte[len];
					s.getAsciiStream().read(tmp,0, len);
					map.put(ToUtils.dbMapKeyToBeanFieldName(keys[i]), new String(tmp));
					s.getAsciiStream().close();*/
					Reader reader = s.getCharacterStream();
					br = new BufferedReader(reader);
					String temp = null;
					StringBuffer sb = new StringBuffer();
					while ((temp=br.readLine()) != null) {
						sb = new StringBuffer();
						sb.append(temp);
					}
					map.put(ToUtils.dbMapKeyToBeanFieldName(keys[i]), sb.toString());
				} catch (Exception e) {
					log.error("", e);
				} finally {
					if(br != null) {
						try {
							br.close();
						} catch (IOException e) {
							log.error("", e);
						}
					}
				}
			}else{
				map.put(ToUtils.dbMapKeyToBeanFieldName(keys[i]), values[i]);
			}
		}
		return map;
	}
}


/**
 * 查询返回对象解析（HashMap）(默认)
 * @author Administrator
 *
 */
@SuppressWarnings({"rawtypes", "serial"})
class ListOrderedMapResultTransformer implements ResultTransformer{
	public final static Logger log = Logger.getLogger(MapResultTransformer.class);
	
	public List transformList(List arg0) {
		return arg0;
	}
	public Object transformTuple(Object[] values, String[] keys) {
		ListOrderedMap map = new ListOrderedMap();
		for (int i = 0; i < keys.length; i++) {
			if(values[i]!=null && SerializableClob.class.equals(values[i].getClass())){
				SerializableClob s = (SerializableClob)values[i];
				try {
					int len = s.getAsciiStream().available();
					byte[] tmp = new byte[len];
					s.getAsciiStream().read(tmp,0, len);
					map.put(ToUtils.dbMapKeyToBeanFieldName(keys[i]), new String(tmp));
					s.getAsciiStream().close();
				} catch (Exception e) {
					log.error("", e);
				} 
			}else{
				map.put(ToUtils.dbMapKeyToBeanFieldName(keys[i]), values[i]);
			}
		}
		return map;
	}
}

/**
 * 查询返回对象解析（JSONObject）
 * @author Administrator
 *
 */
@SuppressWarnings({"rawtypes", "serial"})
class JsonResultTransformer implements ResultTransformer{
	public final static Logger log = Logger.getLogger(JsonResultTransformer.class);

	public List transformList(List arg0) {
		return arg0;
	}

	public Object transformTuple(Object[] values, String[] keys) {
		JSONObject json = new JSONObject();
		for (int i = 0; i < keys.length; i++) {
			if(values[i]!=null && SerializableClob.class.equals(values[i].getClass())){
				SerializableClob s = (SerializableClob)values[i];
				try {
					int len = s.getAsciiStream().available();
					byte[] tmp = new byte[len];
					s.getAsciiStream().read(tmp,0, len);
					json.put(ToUtils.dbMapKeyToBeanFieldName(keys[i]), new String(tmp));
					s.getAsciiStream().close();
				} catch (Exception e) {
					log.error("", e);
				} 
			}else{
				json.put(ToUtils.dbMapKeyToBeanFieldName(keys[i]), values[i]);
			}
		}
		return json;
	}
	
}