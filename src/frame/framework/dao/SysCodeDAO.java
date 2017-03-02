package framework.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import framework.db.pojo.TXtSysCode;
import framework.config.SysDict;
import framework.db.DBUtil;

@Repository("sysCodeDAO")
public class SysCodeDAO {
	@SuppressWarnings("unchecked")
	public List<TXtSysCode> querySysCodeByType(String type) throws Exception {
		DBUtil db = DBUtil.getInstance();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		params.put("flag", SysDict.FLAG_ACT);
		return db.queryByPojo(TXtSysCode.class, params);
	}
}