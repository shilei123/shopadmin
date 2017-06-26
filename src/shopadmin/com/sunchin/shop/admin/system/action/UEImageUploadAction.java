package com.sunchin.shop.admin.system.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScImage;
import com.sunchin.shop.admin.util.ImageServiceUtil;

import framework.action.PageAction;
import framework.db.DBUtil;
import framework.helper.RequestHelper;
import framework.util.ConfigUtil;
import framework.util.FileUtils;

public class UEImageUploadAction extends PageAction {
	private File upfile;
	private String upfileFileName;
	private String upfileContentType;
	
	private static String localUploadPath = ConfigUtil.getInstance().getLocalUploadPath();
	
	public String upload() {
		File dir = new File(localUploadPath);
		if(!dir.exists()) {
			dir.mkdir();
		}
		
        JSONObject map = new JSONObject(); //返回结果信息(UEditor需要)
		InputStream is = null;
		OutputStream os = null;
		try {
			if(this.upfile != null) {
            	String imgType = this.upfileFileName.substring(this.upfileFileName.lastIndexOf(".")+1);
		        String newFileName = System.currentTimeMillis() + "." + imgType;
		        is = new FileInputStream(this.upfile);
	            os = new FileOutputStream(new File(dir.getAbsolutePath(), newFileName));
	            byte[] buffer = new byte[500];
	            while ((is.read(buffer, 0, buffer.length)) != -1) {
	                os.write(buffer);
	            }
	            
	            File newFile = new File(localUploadPath+"/"+newFileName);
	            //上传至图片服务器
	            String savePath = ImageServiceUtil.uploadImg(newFile);
	            if(StringUtils.isNotBlank(savePath)) {
	            	ScImage imgResult = this.getScImage(newFile, savePath, imgType);
	            	DBUtil db = DBUtil.getInstance();
	            	db.insert(imgResult);
	            }
	            
	            //是否上传成功
	            map.put("state", "SUCCESS");
	            //现在文件名称
	            map.put("title", newFileName);
	            //文件原名称 
	            map.put("original", newFileName);
	            //文件类型 .+后缀名
	            map.put("type", "."+imgType);
	            //文件路径
	            map.put("url", "/"+savePath+"/"+newFileName);
	            //文件大小（字节数）
	            map.put("size", FileUtils.getFileSize(upfile)+"");
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
		
		try {
			//UE需要的返因值  
			RequestHelper.getResponse().getWriter().write(map.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private ScImage getScImage(File file, String filePath, String imgType) {
		ScImage img = new ScImage();
		img.setId(UUID.randomUUID().toString());
		img.setImgName(this.upfileFileName);
		img.setFileName(file.getName());
		img.setImgPath(filePath);
		img.setImgSize(FileUtils.getFileSize(file));
		img.setImgType(imgType);
		img.setFlag(FlagEnum.ACT.getCode());
		img.setCreateTime(new Date());
		return img;
    }
	
	public File getUpfile() {
		return upfile;
	}
	
	public void setUpfile(File upfile) {
		this.upfile = upfile;
	}
	
	public String getUpfileFileName() {
		return upfileFileName;
	}
	
	public void setUpfileFileName(String upfileFileName) {
		this.upfileFileName = upfileFileName;
	}
	
	public String getUpfileContentType() {
		return upfileContentType;
	}
	
	public void setUpfileContentType(String upfileContentType) {
		this.upfileContentType = upfileContentType;
	}
	
	
}