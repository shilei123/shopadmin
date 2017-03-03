package framework.action.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONArray;
import framework.action.FenyeBase;
import framework.db.DBUtil;
import framework.db.pojo.TXtCacheTable;
import framework.helper.RequestHelper;
import common.Logger;

@SuppressWarnings({"unchecked","rawtypes"})
public class CacheTableAction extends FenyeBase{
	private final static Logger log = Logger.getLogger(CacheTableAction.class);
	private final HashMap<String,List> cacheTableData = new HashMap();
	
	private String id;
	private String tableName;
	private TXtCacheTable pojo;
	
	public String save(){
		DBUtil db = DBUtil.getInstance();
		if(pojo.getId()==null || "".equals(pojo.getId())){
			pojo.setId(UUID.randomUUID().toString());
			pojo.setTableName(pojo.getTableName().toUpperCase());
			db.insert(pojo);
		}else{
			TXtCacheTable tmp = (TXtCacheTable)db.get(TXtCacheTable.class, pojo.getId());
			tmp.setCacheType(pojo.getCacheType());
			tmp.setTableName(pojo.getTableName().toUpperCase());
			tmp.setQuerySql(pojo.getQuerySql());
			db.update(tmp);
		}
		return "success";
	}
	
	public String query(){
		List data = null;
		if(id!=null && !"".equals(id) && cacheTableData.get("id")!=null){
			data = cacheTableData.get("id");
		}else if(tableName!=null && !"".equals(tableName) && cacheTableData.get(tableName=tableName.toUpperCase())!=null){
			data = cacheTableData.get(tableName);
		}
		
		if(data==null){
			DBUtil db = DBUtil.getInstance();
			HashMap params = new HashMap(1);
			if(id!=null && !"".equals(id)){
				params.put("id", id);
			}else if(tableName!=null && !"".equals(tableName)){
				tableName=tableName.toUpperCase();
				params.put("tableName", tableName);
			}
			
			if(params.size()>0){
				List<TXtCacheTable> list = db.queryByPojo(TXtCacheTable.class, params);
				if(list.size()>0){
					TXtCacheTable cache = list.get(0);
					String sql = null;
					if("table".equals(cache.getCacheType())){
						sql = "select * from "+cache.getTableName();
					}else if("sql".equals(cache.getCacheType())){
						sql = cache.getQuerySql();
					}
					cacheTableData.put(cache.getTableName(), db.queryBySQL(sql, new Object[]{}));
					cacheTableData.put(cache.getId(), cacheTableData.get(cache.getTableName()));
				}
			}
			
			if(id!=null && !"".equals(id) && cacheTableData.get("id")!=null){
				data = cacheTableData.get("id");
			}else if(tableName!=null && !"".equals(tableName) && cacheTableData.get(tableName=tableName.toUpperCase())!=null){
				data = cacheTableData.get(tableName);
			}
		}
		if(data!=null){
			try {
				RequestHelper.wirte(JSONArray.fromObject(data).toString());
			} catch (IOException e) {
				log.error("", e);
			}
		}
		return null;
	}
	
	public String list(){
		DBUtil db = DBUtil.getInstance();
		StringBuffer sql = new StringBuffer("select * from T_Xt_Cache_Table where 1=1 ");
		List params = new ArrayList();
		if(pojo!=null){
			if(pojo.getCacheType()!=null && !"".equals(pojo.getCacheType())){
				sql.append(" and cache_Type=? ");
				params.add(pojo.getCacheType());
			}
			if(pojo.getTableName()!=null && !"".equals(pojo.getTableName())){
				sql.append(" and (table_name like ? or \"sql\" like ?) ");
				params.add("%"+pojo.getTableName()+"%");
				params.add("%"+pojo.getTableName()+"%");
			}
		}
		
		this.total = db.queryCountBySQL(sql.toString(), params);
		this.query(sql.toString(), params, db);
		return "success";
	}
	
	public String delete(){
		DBUtil.getInstance().delete(pojo);
		return "success";
	}
	
	public String refresh(){
		cacheTableData.clear();
		return "success";
	}
	
	public String info(){
		DBUtil db = DBUtil.getInstance();
		this.pojo =(TXtCacheTable)db.get(TXtCacheTable.class, pojo.getId()); 
		return "success";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public TXtCacheTable getPojo() {
		return pojo;
	}

	public void setPojo(TXtCacheTable pojo) {
		this.pojo = pojo;
	}
	
}
