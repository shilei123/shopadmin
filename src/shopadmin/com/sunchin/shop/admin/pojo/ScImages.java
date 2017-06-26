package com.sunchin.shop.admin.pojo;

import java.sql.Timestamp;
import java.util.Date;

public class ScImages implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2110416274724086741L;
	private String id;
	private String imgName;
	private String fileName;
	private String imgType;
	private Double imgSize;
	private String imgPath;
	private Date createTime;
	private String flag;

	public ScImages() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	public Double getImgSize() {
		return imgSize;
	}

	public void setImgSize(Double imgSize) {
		this.imgSize = imgSize;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}