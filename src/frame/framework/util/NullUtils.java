package framework.util;

import java.util.List;
import java.util.Map;

public class NullUtils {
	
	public static boolean isNull(Object... objs){
		for (Object obj : objs) {
			if(obj==null){return false;}
			if(obj instanceof String){
				if(obj.equals("")){return false;}
			}
			if(obj instanceof Integer){
				if(((Integer)obj).intValue()==0){return false;}
			}
			if(obj instanceof Double){
				if(((Double)obj).doubleValue()==0d){return false;}
			}
			if(obj instanceof Float){
				if(((Float)obj).floatValue()==0f){return false;}
			}
			if(obj instanceof Long){
				if(((Long)obj).longValue()==0l){return false;}
			}
			if(obj instanceof Map){
				if(((Map)obj).size()==0){return false;}
			}
			if(obj instanceof List){
				if(((List)obj).size()==0){return false;}
			}
		}
		return true;
	}
}
