package com.sunchin.shop.admin.dirStruct.service;

import java.util.List;
import java.util.Map;

import com.sunchin.shop.admin.pojo.ScDirStruct;

public interface IDirStructService {

	List<Map<String, Object>> queryDirStruct() throws Exception;

	List<ScDirStruct> queryDirParent(String id) throws Exception;

	void delDirectory(String id) throws Exception;

	void save(ScDirStruct directory) throws Exception;

	List<ScDirStruct> queryDirType() throws Exception;

	
	
	
}
