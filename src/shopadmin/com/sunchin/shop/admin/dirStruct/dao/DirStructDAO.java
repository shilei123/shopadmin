package com.sunchin.shop.admin.dirStruct.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScDirStruct;

import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("dirStructDAO")
public class DirStructDAO extends PageDAO{

	@SuppressWarnings("unchecked")
	public List<ScDirStruct> queryDirectory() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flag",FlagEnum.ACT.getCode());
		List<ScDirStruct> directoryList = DBUtil.getInstance().queryByPojo(ScDirStruct.class, params);
		if(directoryList != null && !directoryList.isEmpty()){
			return directoryList;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findDirStruct() {
		StringBuffer sql = new StringBuffer(" select t1.*,t2.dir_name parent_name from sc_dir_struct t1  ");
		sql.append(" left join sc_dir_struct t2 on t1.parent_dir_id=t2.id ");
		sql.append(" where t1.flag=? ");
		List<Map<String, Object>> lists = DBUtil.getInstance().queryBySQL(sql.toString(),FlagEnum.ACT.getCode());
		if(lists != null && !lists.isEmpty()){
			return lists;
		}
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public List<ScDirStruct> queryDirectoryParent(String parent) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flag",FlagEnum.ACT.getCode());
		params.put("parentDirId", parent);
		List<ScDirStruct> directoryList = DBUtil.getInstance().queryByPojo(ScDirStruct.class, params);
		if(directoryList != null && !directoryList.isEmpty()){
			return directoryList;
		}
		return null;
	}

	
	
	
	
	
}
