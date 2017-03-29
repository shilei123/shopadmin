package com.sunchin.shop.admin.directoryStructure.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.directoryStructure.service.IDirectoryStructureService;
import com.sunchin.shop.admin.pojo.ScDirectoryStructure;

import framework.action.PageAction;

public class DirectoryStructureAction extends PageAction{

	@Resource(name="directoryStructureService")
	private IDirectoryStructureService directoryStructureService;
	
	private List<Map<String,Object>> directoryList;
	private ScDirectoryStructure directory;
	private List<ScDirectoryStructure> directoryTypeList;
	private String msg;
	
	/**
	 * 查询
	 * @return
	 */
	public String queryDirectoryStructure(){
		try {
			directoryList = directoryStructureService.queryDirectoryStructure();
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
			directoryStructureService.save(directory);
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
			List<ScDirectoryStructure> list = directoryStructureService.queryDirectoryParent(directory.getId());
			if(list!=null && !list.isEmpty()){
				this.msg = "该类别下已有子类别，无法删除！";
			}else{
				directoryStructureService.delDirectory(directory.getId());
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
	public String queryDirectoryType(){
		try {
			directoryTypeList = directoryStructureService.queryDirectoryType();
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

	public ScDirectoryStructure getDirectory() {
		return directory;
	}

	public void setDirectory(ScDirectoryStructure directory) {
		this.directory = directory;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<ScDirectoryStructure> getDirectoryTypeList() {
		return directoryTypeList;
	}

	public void setDirectoryTypeList(List<ScDirectoryStructure> directoryTypeList) {
		this.directoryTypeList = directoryTypeList;
	}
	
}
