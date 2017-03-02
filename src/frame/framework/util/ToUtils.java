package framework.util;


import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framework.util.tos.ToBigDecimal;
import framework.util.tos.ToBoolean;
import framework.util.tos.ToCalendar;
import framework.util.tos.ToChar;
import framework.util.tos.ToDate;
import framework.util.tos.ToDouble;
import framework.util.tos.ToFloat;
import framework.util.tos.ToInt;
import framework.util.tos.ToLong;
import framework.util.tos.ToShort;
import framework.util.tos.ToSqlDate;
import framework.util.tos.ToString;
import framework.util.tos.ToTimestamp;


/**
 * 各种To
 * @author vivi
 *
 */
public class ToUtils {
	//缓存set方法  提高效率
	private static Map<String,List<Method>> cacheMethod = new HashMap();
	//缓存从数据库sql大写方式的名称转换为属性方式的键
	public static Map<String,String> cacheDbMapKeyToBeanFieldNameMap = new HashMap();
	//缓存set方法去掉前缀，并且将首字母小写
	private static Map<String,String> cacheSetMethodToFieldsStringMap = new HashMap();
	
	/**
	 * 数据查询的内容转属性名称
	 * @param dbMap
	 * @return
	 */
	public static List dbMapListToBeanMapList(List<Map> mapList){
		List result = new ArrayList(mapList.size());
		for (Map map : mapList) {
			result.add(dbMapToBeanMap(map));
		}
		return result;
	}
	
	/**
	 * 数据查询的内容转属性名称
	 * @param dbMap
	 * @return
	 */
	public static Map dbMapToBeanMap(Map dbMap){
		Map tmp = new HashMap(dbMap.size());
		for (Object key: dbMap.keySet()) {
			tmp.put(dbMapKeyToBeanFieldName(key.toString()), dbMap.get(key));
		}
		return tmp;
	}
	
	
	/**
	 * 数据查询的内容转属性名称
	 * @param key
	 * @return
	 */
	public static String dbMapKeyToBeanFieldName(String key){
		if(cacheDbMapKeyToBeanFieldNameMap.get(key)==null){
			char[] ns = key.toLowerCase().toCharArray();
			StringBuffer str = new StringBuffer();
			int len = ns.length-1;
			for (int i = 0; i < ns.length; i++) {
				if(ns[i]=='_' && i<len){
					str.append(Character.toUpperCase(ns[++i]));
				}else{
					str.append(ns[i]);
				}
			}
			cacheDbMapKeyToBeanFieldNameMap.put(key, str.toString());
		}
		return cacheDbMapKeyToBeanFieldNameMap.get(key);
	}
	
	/**
	 * Map List转换为Bean List (未类型转换优化)
	 * @param clas
	 * @param mapList
	 * @return
	 * @throws Exception
	 */
	public static Object mapListToBeanList(Class clas, List<Map> mapList) throws Exception{
		List beanList = new ArrayList(mapList.size());
		for (Map map : mapList) {
			beanList.add(mapToBean(map, clas.newInstance()));
		}
		return beanList;
	}
	
	/**
	 * Map的值赋给Bean (未类型转换优化)
	 * @param map
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static Object mapToBean(Map map, Object bean) throws Exception{
		objectInit(bean);
		List<Method> ms = cacheMethod.get(bean.getClass().getName());
		for (int i = 0; i < ms.size(); i++) {
			String nkey = setMethodToFieldsString(ms.get(i));
			System.out.println(nkey);
			ms.get(i).invoke(bean, to(map.get(nkey), ms.get(0).getParameterTypes()[0]) );
		}
		return bean;
	}
	
	
	public static Object to(Object obj, Class toObjClass){
		if(obj!=null && toObjClass!=null){
			ToImp to1 = toImpMap.get(obj.getClass().getName());
			ToImp to2 = toImpMap.get(toObjClass.getName());
			return to2.parse(to1.parse(obj));
		}
		return null;
	}
	
	private static Map<String,ToImp> toImpMap = new HashMap();
	static{
		toImpMapInit();
	}
	private static void toImpMapInit(){
		toImpMap.put(BigDecimal.class.getName(), new ToBigDecimal());
		toImpMap.put(Boolean.class.getName(), new ToBoolean());
		toImpMap.put(Calendar.class.getName(), new ToCalendar());
		toImpMap.put(Character.class.getName(), new ToChar());
		toImpMap.put(java.util.Date.class.getName(), new ToDate());
		toImpMap.put(java.sql.Date.class.getName(), new ToSqlDate());
		toImpMap.put(Double.class.getName(), new ToDouble());
		toImpMap.put(Float.class.getName(), new ToFloat());
		toImpMap.put(Integer.class.getName(), new ToInt());
		toImpMap.put(Long.class.getName(), new ToLong());
		toImpMap.put(Short.class.getName(), new ToShort());
		toImpMap.put(String.class.getName(), new ToString());
		toImpMap.put(java.sql.Timestamp.class.getName(), new ToTimestamp());
	}
	
	/**
	 * set方法去掉前缀，并且将首字母小写
	 * @param method
	 * @return
	 */
	public static String setMethodToFieldsString(Method method){
		if(cacheSetMethodToFieldsStringMap.get(method.getName()) == null){
			char[] ns = method.getName().toCharArray();
			ns[3] = Character.toLowerCase(ns[3]);//转小写
			cacheSetMethodToFieldsStringMap.put(method.getName(), String.valueOf(ns, 3, ns.length-3));
		}
		return cacheSetMethodToFieldsStringMap.get(method.getName());
	}
	
	/**
	 * 对象初始化，对象为空则new一个
	 * @param obj
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private static Object objectInit(Object obj) throws Exception{
		if(obj == null){
			obj = obj.getClass().newInstance();
		}
		if(cacheMethod.get(obj.getClass().getName()) == null){
			Method[] methods = obj.getClass().getMethods();
			List methodList = new ArrayList();
			for (int i = 0; i < methods.length; i++) {
				if(methods[i].getName().indexOf("set") == 0){
					methodList.add(methods[i]);
				}
			}
			cacheMethod.put(obj.getClass().getName(), methodList);
		}
		return obj;
	}
}