package com.sunchin.shop.admin.directoryStructure.service;

import java.util.List;
import java.util.Map;

import com.sunchin.shop.admin.pojo.ScDirectoryStructure;

public interface IDirectoryStructureService {

	List<Map<String, Object>> queryDirectoryStructure() throws Exception;

	List<ScDirectoryStructure> queryDirectoryParent(String id) throws Exception;

	void delDirectory(String id) throws Exception;

	void save(ScDirectoryStructure directory) throws Exception;

	List<ScDirectoryStructure> queryDirectoryType() throws Exception;

	
	
	
}
