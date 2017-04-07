package com.sunchin.shop.admin.system.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.sunchin.shop.admin.system.service.ImageUploadService;
import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScImage;
import com.sunchin.shop.admin.util.ImageServiceUtil;

import framework.action.PageAction;
import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.util.ConfigUtil;
import framework.util.FileUtils;

public class ImageUploadAction extends PageAction {
	@Resource(name = "imageUploadService")
	private ImageUploadService imageUploadService;
	
	private File img;
	private String imgFileName;
	private String imgContentType;
	private ScImage imgResult; 
	
	private static String localUploadPath = ConfigUtil.getInstance().getLocalUploadPath();
	
	public String query() {
		try {
			PageBean resultData = imageUploadService.queryImage(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String upload() {
		File dir = new File(localUploadPath);
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		InputStream is = null;
		OutputStream os = null;
		try {
			if(this.img != null) {
            	String imgType = this.imgFileName.substring(this.imgFileName.lastIndexOf(".")+1);
		        String newFileName = System.currentTimeMillis() + "." + imgType;
		        is = new FileInputStream(this.img);
	            os = new FileOutputStream(new File(dir.getAbsolutePath(), newFileName));
	            byte[] buffer = new byte[500];
	            while ((is.read(buffer, 0, buffer.length)) != -1) {
	                os.write(buffer);
	            }
	            
	            File newFile = new File(localUploadPath+"/"+newFileName);
	            //上传至图片服务器
	            String savePath = ImageServiceUtil.uploadImg(newFile);
	            if(StringUtils.isNotBlank(savePath)) {
	            	this.imgResult = this.getScImage(newFile, savePath, imgType);
	            	DBUtil db = DBUtil.getInstance();
	            	db.insert(this.imgResult);
	            }
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if(is != null) {
					os.close();
				}
				if(os != null) {
					is.close();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return Action.SUCCESS;
	}
	
	private ScImage getScImage(File file, String filePath, String imgType) {
		ScImage img = new ScImage();
		img.setId(UUID.randomUUID().toString());
		img.setImgName(this.imgFileName);
		img.setFileName(file.getName());
		img.setImgPath(filePath);
		img.setImgSize(FileUtils.getFileSize(file));
		img.setImgType(imgType);
		img.setFlag(FlagEnum.ACT.getCode());
		img.setCreateTime(new Date());
		return img;
    }

	public ScImage getImgResult() {
		return imgResult;
	}

	public void setImgResult(ScImage imgResult) {
		this.imgResult = imgResult;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getImgContentType() {
		return imgContentType;
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}
}