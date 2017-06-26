package framework.util;

import java.util.Date;

public class CommonUtils {
	public static String getString(Object obj) {
		if(obj != null) {
			return obj.toString();
		}
		return null;
	}
	
	public static Double getDouble(Object obj) {
		try {
			return new Double(obj.toString());
		} catch (Exception e) {
			
		}
		return new Double(0);
	}
	
	@SuppressWarnings("deprecation")
	public static Date getDate(Object obj) {
		try {
			return new Date(obj.toString());
		} catch (Exception e) {
			
		}
		return new Date();
	}

	public static Integer getInteger(Object obj) {
		try {
			return new Integer(obj.toString());
		} catch (Exception e) {
			
		}
		return new Integer(0);
	}
	
	public static void main(String[] args) {
		System.out.println(CommonUtils.getDouble(null));
		System.out.println(CommonUtils.getDouble(""));
		System.out.println(CommonUtils.getDouble("1"));
		System.out.println(CommonUtils.getDouble("q"));
	}
}