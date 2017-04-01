package com.sunchin.shop.admin.dirStructure.service;

import java.util.List;
import java.util.Map;

import com.sunchin.shop.admin.pojo.ScDirStructure;

public interface IDirStructureService {

	List<Map<String, Object>> queryDirStructure() throws Exception;

	List<ScDirStructure> queryDirParent(String id) throws Exception;

	void delDirectory(String id) throws Exception;

	void save(ScDirStructure directory) throws Exception;

	List<ScDirStructure> queryDirType() throws Exception;

	
	
	
}
