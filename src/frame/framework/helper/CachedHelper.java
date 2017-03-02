package framework.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framework.db.DBUtil;

/**
 * 缓存管理
 * @author vivi207
 *
 */
public class CachedHelper {
	//缓存对象
	private static HashMap cachedMap = new HashMap();
	
	/**
	 * 获取缓存表对象
	 * @param tabName 表名 自动转换为大写
	 * @param rowKey 每行数据取key做Map的key
	 * @param db
	 */
	public static Map getCachedTableMap(String tabName, String rowKey, DBUtil db){
		tabName = tabName.toUpperCase();
		if(getCached(tabName)==null){
			HashMap data = new HashMap();
			List<Map> list = db.queryBySQL("select * from "+tabName);
			for(Map row : list){
				data.put(row.get(rowKey), row);
			}
			addCached(tabName, data);
		}
		return (Map)getCached(tabName);
	}
	
	/**
	 * 删除缓存表对象
	 * @param tabName
	 * @return
	 */
	public static Map removeCachedTableMap(String tabName){
		tabName = tabName.toUpperCase();
		if(getCached(tabName)!=null){
			return (Map)removeCached(tabName);
		}
		return null;
	}
	
	/**
	 * 获取缓存对象
	 * @param key
	 * @return
	 */
	public static Object getCached(String key){
		return cachedMap.get(key);
	}
	
	/**
	 * 添加缓存对象
	 * @param key
	 * @param value
	 */
	public static void addCached(String key, Object value){
		cachedMap.put(key, value);
	}
	
	/**
	 * 删除缓存
	 * @param key
	 * @return
	 */
	public static Object removeCached(String key){
		return cachedMap.remove(key);
	}
	
	/**
	 * 获取缓存Map
	 * @return
	 */
	public static HashMap getCachedMap() {
		return cachedMap;
	}
	
	/**
	 * 清理缓存Map
	 * @param cachedMap
	 */
	public static void clearCachedMap(HashMap cachedMap) {
		CachedHelper.cachedMap = cachedMap;
	}
}
