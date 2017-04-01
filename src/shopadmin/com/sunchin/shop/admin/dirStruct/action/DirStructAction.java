package com.sunchin.shop.admin.dirStruct.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.dirStruct.service.IDirStructService;
import com.sunchin.shop.admin.pojo.ScDirStruct;

import framework.action.PageAction;

public class DirStructAction extends PageAction{

	@Resource(name="dirStructService")
	private IDirStructService dirStructService;
	
	private List<Map<String,Object>> directoryList;
	private ScDirStruct directory;
	private List<ScDirStruct> directoryTypeList;
	private String msg;
	
	/**
	 * 查询
	 * @return
	 */
	public String queryDirStruct(){
		try {
			directoryList = dirStructService.queryDirStruct();
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
			dirStructService.save(directory);
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
			List<ScDirStruct> list = dirStructService.queryDirParent(directory.getId());
			if(list!=null && !list.isEmpty()){
				this.msg = "该类别下已有子类别，无法删除！";
			}else{
				dirStructService.delDirectory(directory.getId());
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
			directoryTypeList = dirStructService.queryDirType();
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

	public ScDirStruct getDirectory() {
		return directory;
	}

	public void setDirectory(ScDirStruct directory) {
		this.directory = directory;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<ScDirStruct> getDirectoryTypeList() {
		return directoryTypeList;
	}

	public void setDirectoryTypeList(List<ScDirStruct> directoryTypeList) {
		this.directoryTypeList = directoryTypeList;
	}
	
}
