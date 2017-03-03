package framework.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framework.util.json.JsonGetValueCalender;
import framework.util.json.JsonGetValueDate;
import framework.util.json.JsonGetValueNumber;
import framework.util.json.JsonGetValueString;

public class JsonUtils {
	private static HashMap<Class,IJsonGetValue> classMap = new HashMap();
	static{
		classMap.put(Integer.class, new JsonGetValueNumber());
		classMap.put(Double.class, new JsonGetValueNumber());
		classMap.put(Float.class, new JsonGetValueNumber());
		classMap.put(Short.class, new JsonGetValueNumber());
		classMap.put(Byte.class, new JsonGetValueNumber());
		classMap.put(BigDecimal.class, new JsonGetValueNumber());
		classMap.put(String.class, new JsonGetValueString());
		
		classMap.put(java.util.Date.class, new JsonGetValueDate());
		classMap.put(java.sql.Timestamp.class, new JsonGetValueDate());
		classMap.put(java.sql.Date.class, new JsonGetValueDate());
		classMap.put(java.util.Calendar.class, new JsonGetValueCalender());
	}
	
	/**
	 * 注册一个json值解析
	 * @param clazz
	 * @param jsonGetValue
	 */
	public static void registerJsonGetValue(Class clazz, IJsonGetValue jsonGetValue){
		classMap.put(clazz, jsonGetValue);
	}
	
	/**
	 * 删除一个json值解析
	 * @param clazz
	 * @param jsonGetValue
	 */
	public static void removreJsonGetValue(Class clazz){
		classMap.remove(clazz);
	}
	
	/**
	 * 简单List<Map>转Json字符串（不支持递归）
	 * @param data
	 * @return
	 */
	public static String simpleListToJson(List<Map> data){
		if(data==null){return null;}
		StringBuffer json = new StringBuffer("[");
		String mark = "";
		for(Map map : data){
			json.append(mark).append(simpleMapToJson(map));
			mark = ",";
		}
		json.append("]");
		return json.toString();
	}
	
	/**
	 * 简单Map转Json字符串（不支持递归）
	 * @param data
	 * @return
	 */
	public static String simpleMapToJson(Map data){
		if(data==null){return null;}
		String mark = "";
		StringBuffer json = new StringBuffer("{");
		for(Object key : data.keySet()){
			json.append(mark).append("\""+String.valueOf(key)+"\"").append(":").append(getValue(data.get(key)));
			mark = ",";
		}
		json.append("}");
		return json.toString();
	}
	
	private static String getValue(Object obj){
		if(obj==null){return "null";}
		IJsonGetValue getValue = classMap.get(obj.getClass());
		if(getValue==null){
			getValue = classMap.get(String.class);
		}
		return getValue.getValue(obj);
	}
}
