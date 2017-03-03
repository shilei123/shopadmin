package framework.util;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Logger;

public class BeanUtils {
	private static final Logger log = Logger.getLogger(BeanUtils.class);
	private static final HashMap<Class,Map<String,Map<String,Method>>> cache = new HashMap();
	private static final String[] pojoNotNames  = new String[]{"id","createTime","updateTime","flag","createUserId","createOrgId"};

	/**
	 * hibernate pojo 赋值,排除属性名称"id","crateTime","updateTime","flag","createUserId","createOrgId"
	 * @param objList 复制对象
	 * @param fromList 赋值对象
	 * @param names 属性名称附加
	 * @throws Exception
	 */
	public static void copyPojoList(List objList , List fromList, String... names) throws Exception{
		int len = fromList.size();
		for (int i = 0; i < len; i++) {
			copyPojo(objList.get(i), fromList.get(i), names);
		}
	}
	/**
	 * hibernate pojo 赋值,排除属性名称"id","crateTime","updateTime","flag","createUserId","createOrgId"
	 * @param obj 复制对象
	 * @param fromObj 赋值对象
	 * @param names 属性名称附加
	 * @throws Exception
	 */
	public static void copyPojo(Object obj , Object fromObj, String... names) throws Exception{
		String[] pnames = new String[pojoNotNames.length+names.length];
		
		System.arraycopy(pojoNotNames, 0, pnames, 0, pojoNotNames.length);
		System.arraycopy(names, 0, pnames, pojoNotNames.length, names.length);
		copyNotIn(obj, fromObj, pnames);
	}
	
	/**
	 * getset赋值
	 * @param obj 复制对象
	 * @param fromObj 赋值对象
	 * @param names 属性名称(全复制可不传)
	 * @throws Exception
	 */
	public static void copyInList(List objList , List fromList, String... names) throws Exception{
		int len = fromList.size();
		for (int i = 0; i < len; i++) {
			copyIn(objList.get(i), fromList.get(i), names);
		}
	}
	
	/**
	 * getset赋值
	 * @param obj 复制对象
	 * @param fromObj 赋值对象
	 * @param names 属性名称(全复制可不传)
	 * @throws Exception
	 */
	public static void copyIn(Object obj , Object fromObj, String... names) throws Exception{
		copy(obj, fromObj, true, names);
	}
	
	
	/**
	 * getset赋值
	 * @param obj 复制对象
	 * @param fromObj 赋值对象
	 * @param names 属性名称(全复制可不传)
	 * @throws Exception
	 */
	public static void copyNotInList(List objList , List fromList, String... names) throws Exception{
		int len = fromList.size();
		for (int i = 0; i < len; i++) {
			copyNotIn(objList.get(i), fromList.get(i), names);
		}
	}
	
	
	/**
	 * getset赋值
	 * @param obj 复制对象
	 * @param fromObj 赋值对象
	 * @param names 排除属性名称(全复制可不传)
	 * @throws Exception
	 */
	public static void copyNotIn(Object obj , Object fromObj, String... names) throws Exception{
		copy(obj, fromObj, false, names);
	}
	
	/**
	 * getset赋值
	 * @param obj 复制对象
	 * @param fromObj 赋值对象
	 * @param isIn 是否
	 * @param names 属性名称(全复制可不传)
	 * @throws Exception
	 */
	public static void copy(Object obj , Object fromObj, boolean isIn, String... names) throws Exception{
		Map<String,String> inNameMap = new HashMap(names.length);
		List<String> inNameList = null;
		boolean isInName = true;
		if(names.length>0){
			inNameList = Arrays.asList(names);
			isInName = false;
			String inName = null;
			for (int i = 0; i < names.length; i++) {
				inName = inNameList.get(i);
				inNameList.set(i, "set"+Character.toUpperCase(inName.charAt(0))+inName.substring(1,inName.length()));
			}
		}
		
		Map<String,Method> map = getMethodMap(obj).get("get");
		Map<String,Method> fromMap = getMethodMap(obj).get("set");
		Method get=null,set = null;
		try {
			for(String name:fromMap.keySet()){
				set = fromMap.get(name);
				get = map.get(name.replace("set", "get"));
				if(get!=null && (isInName || isIn==inNameList.contains(name))){
					set.invoke(fromObj, get.invoke(obj));
				}
			}
		} catch (Exception e) {
			log.error("",e);
			throw new Exception("copy error",e);
		}

	}
	
	/**
	 * 获取getset方法Map，并按get set 保存到Map
	 * @param obj
	 * @return
	 */
	private static Map<String,Map<String,Method>> getMethodMap(Object obj){
		if(cache.get(obj.getClass())==null){
			Method[] methods = obj.getClass().getDeclaredMethods();
			HashMap<String,Method> getMap = new HashMap();
			HashMap<String,Method> setMap = new HashMap();
			for(Method m:methods){
				if(m.getName().indexOf("get")==0){
					getMap.put(m.getName(), m);
				}
				if(m.getName().indexOf("set")==0){
					setMap.put(m.getName(), m);
				}
			}
			Map<String,Map<String,Method>> map = new HashMap(2);
			map.put("get", getMap);
			map.put("set", setMap);
			cache.put(obj.getClass(), map);
		}
		return cache.get(obj.getClass());
	}
}
