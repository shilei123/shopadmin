package framework.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;

public class HttpUtil {
	private final static Logger LOG = Logger.getLogger(HttpUtil.class);
	public static JsonConfig jsonConfig = new JsonConfig();
	
    static{
    	jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));  
    	jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
    	jsonConfig.registerJsonValueProcessor(oracle.sql.TIMESTAMP.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
    }
    
    public static Map<String,Map> mapListToMap(List<Map> list, String[] keys, String join){
    	if(list==null){
    		return new HashMap(0);
    	}
    	HashMap<String,Map> result = new HashMap();
    	for(Map map : list){
    		result.put(getJoinKey(map,keys,join), map);
    	}
    	return result;
    }
    
    private static String getJoinKey(Map map, String[] keys, String join){
    	StringBuffer key = new StringBuffer();
    	String m = "";
    	for(String k : keys){
    		key.append(m).append(map.get(k));
    		m = join;
    	}
    	return key.toString();
    }
    
	public static Map mapAddKey(Map data, String addKey){
		if(data==null){
			return new HashMap(0);
		}
		LinkedHashMap result = new LinkedHashMap(data.size());
		for(Object key : data.keySet()){
			result.put(addKey+"."+key,  data.get(key));
		}
		return result;
	}
	
	public static void write(HttpServletResponse response, Object msg) throws IOException{
		write(response, msg==null?"":JSONObject.fromObject(msg));
	}
	public static void write(HttpServletResponse response, List<Map> msg) throws IOException{
		write(response, JSONArray.fromObject(msg, jsonConfig).toString());
	}
	public static void write(HttpServletResponse response, Map msg) throws IOException{
		write(response,JSONObject.fromObject(msg, jsonConfig));
	}
	
	public static void write(HttpServletResponse response, JSONObject msg) throws IOException{
		write(response,msg.toString());
	}
	
	public static void write(HttpServletResponse response, JSONArray msg) throws IOException{
		write(response,msg.toString());
	}
	
	public static void write(HttpServletResponse response, String msg) throws IOException{
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		LOG.debug("write:"+(msg.length()>1000?msg.substring(0,1000)+"...":msg));
		response.getWriter().print(msg);
	}
	
	public static Map<String , String> getMap(Map<String,Object[]> data, String key){
		LinkedHashMap<String , String> result = new LinkedHashMap<String, String>();
		for(String k : data.keySet()){
			if(k.indexOf(".")>-1){
				String[] kvs = k.split("\\.");
				if(key.equals(kvs[0])){
					result.put(kvs[1], data.get(k)[0]==null?null:String.valueOf(data.get(k)[0]));
				}
			}
		}
		return result;
	}
}
