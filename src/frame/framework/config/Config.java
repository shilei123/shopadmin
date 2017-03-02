package framework.config;

import java.util.HashMap;
import java.util.Map;

public class Config {
	public final static String JSP_INCLUDE_HTML = "jsp_include_html";//jsp页面导入的html
	public final static String FILEUPLOAD_PATH_NAME = "fileupload_path";//文件上传路径——名称
	public final static String FILEUPLOAD_USERHEADIMG_ROOT = "/userheadimg/";//用户头像图片存放目录
	public final static String FILEUPLOAD_USERFILE_ROOT = "/userfile/";//用户资料库
	
	private final static Map<String,String> configMap = new HashMap();
	
	public static void initConfigMap(Map<String,String> map){
		configMap.putAll(map);
	}
	
	public static String get(String key){
		return configMap.get(key);
	}
	
	/**
	 * 获取文件上传文件夹目录
	 * @param key
	 * @return
	 */
	public static String getFileUploadRoot(){
		return configMap.get(FILEUPLOAD_PATH_NAME);
	}
	
	/**
	 * 获取用户头像头像目录
	 * @param key
	 * @return
	 */
	public static String getUserHeadImgRoot(){
		return configMap.get(FILEUPLOAD_PATH_NAME)+FILEUPLOAD_USERHEADIMG_ROOT;
	}
}
