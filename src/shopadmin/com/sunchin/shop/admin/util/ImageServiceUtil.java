package com.sunchin.shop.admin.util;

import java.io.File;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

import framework.util.ConfigUtil;

public class ImageServiceUtil {
	//图片服务器的上传服务URL
	public static String imgServerUploadURL = ConfigUtil.getInstance().getImgServerUploadURL();
	public static String attachServerUploadURL = ConfigUtil.getInstance().getAttachServerUploadURL();
	
	public static String uploadImg(File tartgetFile) {
		return upload2Server(tartgetFile, imgServerUploadURL);
	}
	
	public static String uploadAttach(File tartgetFile) {
		return upload2Server(tartgetFile, attachServerUploadURL);
	}
	
	private static String upload2Server(File targetFile, String targetURL) {
		//boolean flag = false;
		PostMethod filePost = null;
		String result = null;
		try {
			filePost = new PostMethod(targetURL);
			// 通过以下方法可以模拟页面参数提交
			Part[] parts = { (Part) new FilePart(targetFile.getName(), targetFile) };
			filePost.setRequestEntity(new MultipartRequestEntity((org.apache.commons.httpclient.methods.multipart.Part[]) parts, filePost.getParams()));
			HttpClient client = new HttpClient();
			/*client.setConnectionTimeout(30000);
			client.setTimeout(30000);*/
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			int status = client.executeMethod(filePost);
			
			if (status == HttpStatus.SC_OK) { //上传成功
				targetFile.delete(); //删除本地
				result = filePost.getResponseBodyAsString();
				//flag = true;
			} else { //上传失败
				//flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(filePost != null) {
				filePost.releaseConnection();
			}
		}
		return result;
	}	
}