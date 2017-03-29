package com.sunchin.shop.admin.directoryStructure.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScDirectoryStructure;

import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("directoryStructureDAO")
public class DirectoryStructureDAO extends PageDAO{

	@SuppressWarnings("unchecked")
	public List<ScDirectoryStructure> queryDirectory() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flag",FlagEnum.ACT.getCode());
		List<ScDirectoryStructure> directoryList = DBUtil.getInstance().queryByPojo(ScDirectoryStructure.class, params);
		if(directoryList != null && !directoryList.isEmpty()){
			return directoryList;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findDirectoryStructure() {
		StringBuffer sql = new StringBuffer(" select t1.*,t2.directory_name parent_name from sc_directory_structure t1  ");
		sql.append(" left join sc_directory_structure t2 on t1.parent_directory_id=t2.id ");
		sql.append(" where t1.flag=? ");
		List<Map<String, Object>> lists = DBUtil.getInstance().queryBySQL(sql.toString(),FlagEnum.ACT.getCode());
		if(lists != null && !lists.isEmpty()){
			return lists;
		}
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public List<ScDirectoryStructure> queryDirectoryParent(String parent) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flag",FlagEnum.ACT.getCode());
		params.put("parentDirectoryId", parent);
		List<ScDirectoryStructure> directoryList = DBUtil.getInstance().queryByPojo(ScDirectoryStructure.class, params);
		if(directoryList != null && !directoryList.isEmpty()){
			return directoryList;
		}
		return null;
	}

	
	
	
	
	
}
