package com.sunchin.shop.admin.dirStructure.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.dirStructure.service.IDirStructureService;
import com.sunchin.shop.admin.pojo.ScDirStructure;

import framework.action.PageAction;

public class DirStructureAction extends PageAction{

	@Resource(name="dirStructureService")
	private IDirStructureService dirStructureService;
	
	private List<Map<String,Object>> directoryList;
	private ScDirStructure directory;
	private List<ScDirStructure> directoryTypeList;
	private String msg;
	
	/**
	 * 查询
	 * @return
	 */
	public String queryDirStructure(){
		try {
			directoryList = dirStructureService.queryDirStructure();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	/**
	 * 保存
	 * @return
	 */
	public String save(){
		try {
			dirStructureService.save(directory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		try {
			List<ScDirStructure> list = dirStructureService.queryDirParent(directory.getId());
			if(list!=null && !list.isEmpty()){
				this.msg = "该类别下已有子类别，无法删除！";
			}else{
				dirStructureService.delDirectory(directory.getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询目录
	 * @return
	 */
	public String queryDirType(){
		try {
			directoryTypeList = dirStructureService.queryDirType();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	

	public List<Map<String, Object>> getDirectoryList() {
		return directoryList;
	}

	public void setDirectoryList(List<Map<String, Object>> directoryList) {
		this.directoryList = directoryList;
	}

	public ScDirStructure getDirectory() {
		return directory;
	}

	public void setDirectory(ScDirStructure directory) {
		this.directory = directory;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<ScDirStructure> getDirectoryTypeList() {
		return directoryTypeList;
	}

	public void setDirectoryTypeList(List<ScDirStructure> directoryTypeList) {
		this.directoryTypeList = directoryTypeList;
	}
	
}
