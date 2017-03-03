package framework.config;

import java.util.HashMap;
import java.util.Map;

public class ImageContentType {
	private final static Map<String,String> imgMap = new HashMap();
	static{
		imgMap.put("jpg", "image/jpeg");
		imgMap.put("jpeg", "image/jpeg");
		imgMap.put("jpe", "image/jpeg");
		imgMap.put("gif", "image/gif");
		imgMap.put("png", "image/png");
		imgMap.put("bpm", "image/bpm");
	}
	
	public static String getContentType(String hz){
		String ct = imgMap.get(String.valueOf(hz).toLowerCase());
		return ct==null?"":ct;
	}
}
