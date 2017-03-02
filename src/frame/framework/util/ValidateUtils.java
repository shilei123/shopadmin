package framework.util;


public class ValidateUtils {
	
	public static boolean isNull(Object obj){
		return obj==null;
	}
	
	public static boolean isNullStr(String obj){
		return isNull(obj)?true:obj.trim().length()==0;
	}
	
	
	public static boolean isStringLen(String obj, int s){
		if(isNullStr(obj)){
			return true;
		}
		return obj.length()>s;
	}
	
	public static boolean isStringLen(String obj, int s, int e){
		if(isNullStr(obj)){
			return true;
		}
		return obj.length()>s || obj.length()<2;
	}
}
