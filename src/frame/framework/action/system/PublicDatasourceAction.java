package framework.action.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import framework.action.FenyeBase;
import framework.bean.UserMsg;
import framework.db.DBUtil;
import framework.db.pojo.TPbDatasource;
import framework.helper.RequestHelper;
import framework.util.BeanUtils;
import framework.util.JsonUtils;

@SuppressWarnings({"unchecked","rawtypes"})
public class PublicDatasourceAction extends FenyeBase{
	private static final Logger log = Logger.getLogger(PublicDatasourceAction.class);
	
	private TPbDatasource source;
	private String message;
	
	public String save(){
		DBUtil db = DBUtil.getInstance();
		try {
			db.queryBySQL(source.getSourceSql(), null, 1, 1);
		} catch (Exception e) {
			this.message = "不能执行的SQL，问题："+e.toString();
			return "json";
		}
		
		if(source.getId()==null || "".equals(source.getId())){
			source.setId(UUID.randomUUID().toString());
			db.insert(source);
		}else{
			TPbDatasource pojo = (TPbDatasource)db.get(TPbDatasource.class, source.getId());
			try {
				BeanUtils.copyPojo(source, pojo);
				db.update(pojo);
			} catch (Exception e) {
				log.error("", e);
				this.message = "保存异常";
			}
		}
		this.message = "success";
		return "json";
	}
	
	public String list(){
		DBUtil db = DBUtil.getInstance();
		StringBuffer sql = new StringBuffer("select d.id, d.source_name, d.source_remark, u.u_name user_name, d.create_time, d.update_time" +
				" from T_Pb_Datasource d left join t_xt_user u on d.create_user_id=u.u_id where 1=1 ");
		List params = new ArrayList();
		if (this.queryParams == null) {
			this.queryParams = new HashMap(0);
		}
		DBUtil.sqlAddByLike(sql, params, "d.source_Name", queryParams.get("name"));
		this.total = db.queryCountBySQL(sql.toString() +" order by d.create_time desc", params);
		this.dataRows = db.queryBySQL(sql.toString(), params, this.getPage()
				* this.getRowCount() - this.getRowCount(), this.getRowCount());

		return "json";
	}
	
	public String data(){
		DBUtil db = DBUtil.getInstance();
		TPbDatasource pojo = (TPbDatasource)db.get(TPbDatasource.class, source.getId());
		if(pojo==null){return "json";}
		String sql = pojo.getSourceSql();
		StringBuffer where = new StringBuffer();

		//绑定映射参数
		List params = new ArrayList();
		buildWhereSql(pojo.getWhereSql(), where, params);
		String exeSql = sql+ where.toString();
		
		//替换session值
		if(exeSql.indexOf("@")>-1){
			Map<String,String> user = getUserMap();
			for(String key : user.keySet()){
				exeSql = exeSql.replaceAll(key, user.get(key));
			}
		}
		
		//解决json解析map不按key顺序的问题  使用jsonobject
		boolean resultSortKey = false;
		if("true".equals(RequestHelper.getParameter("resultSortKey"))){
			resultSortKey = true;
			db.setResultTransformer(DBUtil.RESULT_TRANSFORMER_LIST_ORDER_MAP);
		}
		this.dataRows = db.queryBySQL(exeSql, params);
		this.total = this.dataRows.size();
		db.setResultTransformer();
		
		//combo list 直接返回数据行json对象
		String returnType = RequestHelper.getParameter("returnType");
		if("combo".equals(returnType) || "list".equals(returnType)){
			try {
				if(resultSortKey){
					RequestHelper.wirte(JsonUtils.simpleListToJson(this.dataRows));
				}else{
					RequestHelper.wirte(JSONArray.fromObject(this.dataRows).toString());
				}
			} catch (IOException e) {
				log.error(e);
			}
			return null;
		}else{
			return "json";
		}
	}
	
	/**
	 * where条件sql语句构建
	 * @param whereJson
	 * @param where
	 * @param params
	 */
	private void buildWhereSql(String whereJson, StringBuffer where, List params){
		if(whereJson==null || whereJson.length()==0 || "[]".equals(whereJson)){
			return ;
		}
		if(queryParams==null || queryParams.size()==0){
			return ;
		}
		
		JSONArray arr = JSONArray.fromObject(whereJson);
		int len = arr.size();
		for (int i = 0; i < len; i++) {
			JSONObject obj = arr.getJSONObject(i);
			String key = obj.getString("name");
			String sql = obj.getString("sql");
			String value = queryParams.get(key);
			if(value!=null&&!"".equals(value)){
				where.append(sql);
				if(sql.indexOf("?")>-1){
					params.add(value);
				}
			}
		}
		
	}
	
	private Map<String,String> getUserMap(){
		UserMsg user = RequestHelper.getUser();
		
		StringBuffer roles = new StringBuffer();
		Map<String,String> roleMap = user.getRole();
		String mark = "";
		for(String key : roleMap.keySet()){
			roles.append(mark).append("'").append(key).append("'");
			mark = ",";
		}
		
		Map map = new HashMap();
		map.put("@userId@", user.getUId());
		map.put("@deptId@", user.getDeptId());
		map.put("@orgId@", user.getOrgId());
		map.put("@orgCode@", user.getOrgCode());
		map.put("@orgPath@", user.getOrgPath());
		map.put("@role@", roles.toString());
		return map;
	}
	
	public String delete(){
		DBUtil db = DBUtil.getInstance();
		TPbDatasource pojo = (TPbDatasource)db.get(TPbDatasource.class, source.getId());
		if(pojo!=null){
			db.delete(pojo);
			this.message = "success";
		}else{
			this.message = "数据源不存在";
		}
		return "json";
	}

	public String info(){
		DBUtil db = DBUtil.getInstance();
		this.source = (TPbDatasource)db.get(TPbDatasource.class, source.getId());
		return "json";
	}
	
	//--------------get set begin
	
	public TPbDatasource getSource() {
		return source;
	}

	public void setSource(TPbDatasource source) {
		this.source = source;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	//--------------get set end
}
