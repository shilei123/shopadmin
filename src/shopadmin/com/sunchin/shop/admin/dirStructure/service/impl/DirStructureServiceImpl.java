package com.sunchin.shop.admin.dirStructure.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.dirStructure.dao.DirStructureDAO;
import com.sunchin.shop.admin.dirStructure.service.IDirStructureService;
import com.sunchin.shop.admin.pojo.ScDirStructure;

import framework.db.DBUtil;
import framework.util.ComparatorCategoryVO;

@Repository("dirStructureService")
public class DirStructureServiceImpl implements IDirStructureService{

	@Resource(name="dirStructureDAO")
	private DirStructureDAO dirStructureDAO;

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map<String, Object>> queryDirStructure() throws Exception {
	List<Map<String,Object>> directoryList = dirStructureDAO.findDirectoryStructure();
	if(directoryList == null || directoryList.isEmpty()){
		return null;
	}
	Map root = null;
	Map<String,Object> temp = new TreeMap<String,Object>();
	for(Map pojo : directoryList){
		Map node = new TreeMap();
		node.put("PkId",pojo.get("id")); //主键
		node.put("text",pojo.get("directoryName"));//栏目名称
		node.put("parentDirectoryId", pojo.get("parentDirectoryId")); //上级栏目id
		node.put("parentName",pojo.get("parentName"));//上级栏目名称
		node.put("levels", pojo.get("levels")); //所属级别
		Map attributes = new HashMap(3);
		attributes.put("cateOrder", pojo.get("cateOrder")); //排序
		attributes.put("isuse", pojo.get("isuse")); //是否有效
		node.put("attributes", attributes);
		temp.put(pojo.get("id").toString(),node);
		//找出根目录
		if("0".equals(pojo.get("levels"))){
			root = node;
		}
	}
	
	//循环找出层级关系
	for(String key : temp.keySet()){
		Map node = (Map) temp.get(key);
		Object parentId = node.get("parentDirectoryId");
		if(parentId == null){
			continue;
		}
		Map parentMap = (Map) temp.get(parentId);
		if(parentMap != null){
			if(parentMap.get("children") == null) {
				parentMap.put("children", new ArrayList());
			}
			((ArrayList)parentMap.get("children")).add(node);
		}
	}
	
	//循环找出是否有子节点，是否能展开
	for(String key : temp.keySet()) {
		Map node = (Map) temp.get(key);
		if(node.get("children") != null) {
			List perforManceList = (ArrayList) node.get("children");
			if(!perforManceList.isEmpty()) {
				Collections.sort(perforManceList, new ComparatorCategoryVO());
				node.put("state", "closed"); //节点状态：关闭
			}
		} 
	}
	
	//perforManceList
	List<Map<String, Object>> trees = new ArrayList(1);
	if(root != null){
		trees.add(root);
	}
	
	if(trees != null && !trees.isEmpty()){
		return trees;
	}
	return null;
	}

	/**
	 * 查看该栏目有否有子栏目
	 */
	@Override
	public List<ScDirStructure> queryDirParent(String parent)throws Exception {
		List<ScDirStructure>  directoryList =	dirStructureDAO.queryDirectoryParent(parent);
		return directoryList;
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void delDirectory(String id) throws Exception {
		DBUtil db = DBUtil.getInstance();
		ScDirStructure vo = (ScDirStructure) db.get(ScDirStructure.class, id);
		vo.setFlag(FlagEnum.HIS.getCode());
		db.update(vo);
	}

	/**
	 * 保存
	 */
	@SuppressWarnings("unused")
	@Override
	public void save(ScDirStructure directory) throws Exception {
		if (directory == null) {
			return;
		}
		DBUtil db = DBUtil.getInstance();
		// 新增
		if (StringUtils.isBlank(directory.getId())) {
			directory.setId(UUID.randomUUID().toString());
			directory.setFlag(FlagEnum.ACT.getCode());
			directory.setCreateTime(new Date());
			DBUtil.getInstance().insert(directory);
		} else { // 修改
			ScDirStructure vo = (ScDirStructure) DBUtil.getInstance().get(ScDirStructure.class, directory.getId());
			vo.setDirName(directory.getDirName());
			vo.setDirPath(directory.getDirPath());
			vo.setOrder(directory.getOrder());
			vo.setIsuse(directory.getIsuse());
			vo.setUpdateTime(new Date());
			DBUtil.getInstance().update(vo);
		}
	}

	/**
	 * 查询目录
	 */
	@Override
	public List<ScDirStructure> queryDirType() throws Exception {
		List<ScDirStructure> directory = dirStructureDAO.queryDirectory();
		return directory;
	}
}
