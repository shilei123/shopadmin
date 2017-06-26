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

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScAttachments;
import com.sunchin.shop.admin.system.service.AttachUploadService;
import com.sunchin.shop.admin.util.ImageServiceUtil;

import framework.action.PageAction;
import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.util.ConfigUtil;
import framework.util.FileUtils;

/**
 * @author yangchaowen
 * 2017年3月14日
 * 附件管理Action
 */
public class AttachUploadAction extends PageAction {
	@Resource(name = "attachUploadService")
	private AttachUploadService attachUploadService;
	
	private File attach;
	private String attachFileName;
	private String attachContentType;
	private ScAttachments attachVO; 
	
	private static String localUploadPath = ConfigUtil.getInstance().getLocalUploadPath();
	
	public String query() {
		try {
			PageBean resultData = attachUploadService.queryAttachs(this.getPageBean());
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
			if(this.attach != null) {
            	String attachType = this.attachFileName.substring(this.attachFileName.lastIndexOf(".")+1);
		        String newFileName = System.currentTimeMillis() + "." + attachType;
		        is = new FileInputStream(this.attach);
	            os = new FileOutputStream(new File(dir.getAbsolutePath(), newFileName));
	            byte[] buffer = new byte[500];
	            while ((is.read(buffer, 0, buffer.length)) != -1) {
	                os.write(buffer);
	            }
	            
	            File newFile = new File(localUploadPath+"/"+newFileName);
	            //上传至图片服务器
	            String savePath = ImageServiceUtil.uploadAttach(newFile);
	            if(StringUtils.isNotBlank(savePath)) {
	            	this.attachVO = this.getScAttachments(newFile, savePath, attachType);
	            	DBUtil db = DBUtil.getInstance();
	            	db.insert(this.attachVO);
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
	
	private ScAttachments getScAttachments(File file, String filePath, String imgType) {
		ScAttachments attach = new ScAttachments();
		attach.setId(UUID.randomUUID().toString());
		attach.setKind(this.attachVO.getKind());
		attach.setAttachName(this.attachFileName);
		attach.setFileName(file.getName());
		attach.setAttachPath(filePath);
		attach.setAttachSize(FileUtils.getFileSize(file));
		attach.setAttachType(imgType);
		attach.setFlag(FlagEnum.ACT.getCode());
		attach.setCreateTime(new Date());
		return attach;
    }

	public AttachUploadService getAttachUploadService() {
		return attachUploadService;
	}

	public void setAttachUploadService(AttachUploadService attachUploadService) {
		this.attachUploadService = attachUploadService;
	}

	public File getAttach() {
		return attach;
	}

	public void setAttach(File attach) {
		this.attach = attach;
	}

	public String getAttachFileName() {
		return attachFileName;
	}

	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}

	public String getAttachContentType() {
		return attachContentType;
	}

	public void setAttachContentType(String attachContentType) {
		this.attachContentType = attachContentType;
	}

	public ScAttachments getAttachVO() {
		return attachVO;
	}

	public void setAttachVO(ScAttachments attachVO) {
		this.attachVO = attachVO;
	}
	
}