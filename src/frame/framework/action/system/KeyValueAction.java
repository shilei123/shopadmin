package framework.action.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import org.apache.log4j.Logger;import frame.framework.db.pojo.TXtSysCode;


import framework.action.FenyeBase;
import framework.config.SysDict;
import framework.db.DBUtil;
import framework.db.pojo.TXtSysCode;

@SuppressWarnings({"unchecked","rawtypes"})
public class KeyValueAction  extends FenyeBase {
	//private final static Logger log = Logger.getLogger(KeyValueAction.class);
	private final static String SQL_ALL = " select t.* from sys_code t where t.flag=? ";
	private TXtSysCode kv;
	private String ids;
	
	public String list(){
		DBUtil db = DBUtil.getInstance();
		StringBuffer sql = new StringBuffer(SQL_ALL);
		List params = new ArrayList();
		params.add(SysDict.FLAG_ACT);
		
		if(this.queryParams != null) {
			DBUtil.sqlAddByLike(sql, params, " t.type ", queryParams.get("key"));
			DBUtil.sqlAddByLike(sql, params, " t.code ", queryParams.get("code"));
			DBUtil.sqlAddByLike(sql, params, " t.label ", queryParams.get("label"));
			DBUtil.sqlAddByLike(sql, params, " t.remark ", queryParams.get("remark"));
		}
		sql.append(" ORDER BY t.id ");
		
		this.query(sql.toString(), params, db);
		int count = db.queryCountBySQL(sql.toString(), params);
		this.setTotal(count);	
		return "success";
	}
	
	public String query() {
		if(this.queryParams != null){
			StringBuffer sql = new StringBuffer(SQL_ALL);
			List params = new ArrayList();
			params.add(SysDict.FLAG_ACT);
			DBUtil.sqlAdd(sql, params, " t.type ", queryParams.get("kv_key"));
			DBUtil.sqlAdd(sql, params, " t.code ", queryParams.get("kv_value"));
			this.query(sql.toString(), params);
		}
		return "success";
	}
	
	public String save(){
		DBUtil db = DBUtil.getInstance();
		if (kv.getId()==null) { // 保存
			String sql = "insert into sys_code(type,code,label,remark,flag) values (?, ?, ?, ?, ?)";
			db.executeSQL(sql, kv.getType(), kv.getCode(), kv.getLabel(), kv.getRemark(), SysDict.FLAG_ACT);
		} else { 
			TXtSysCode pojo = null;
			Map<String, Object> params = new HashMap<String, Object>(1);
			params.put("id", kv.getId());
			List<TXtSysCode> list = db.queryByPojo(TXtSysCode.class, params);
			if (list != null && !list.isEmpty()) {
				pojo = list.get(0);
			}
			if (pojo != null) {
				pojo.setType(kv.getType());
				pojo.setCode(kv.getCode());
				pojo.setLabel(kv.getLabel());
				pojo.setRemark(kv.getRemark());
				db.update(pojo);
			}
		}
		//db.commit();
		return "success";
	}
	
	public String delete(){
		String[] ids = this.ids.split(",");
		for(String id : ids){
			String sql = " update sys_code t set t.flag=? where t.id=? ";
			DBUtil.getInstance().executeSQL(sql, SysDict.FLAG_HIS, id);
		}
		
		return "success";
	}

	public TXtSysCode getKv() {
		return kv;
	}

	public void setKv(TXtSysCode kv) {
		this.kv = kv;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
}