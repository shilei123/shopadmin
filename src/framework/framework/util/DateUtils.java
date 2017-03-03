package framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	private static String[] PATTERN_DATAS = new String[]{"","","","","yyyy","","","yyyy-MM","","","yyyy-MM-dd","","","yyyy-MM-dd hh","","","yyyy-MM-dd hh:mm","","","yyyy-MM-dd hh:mm:ss"};
	private static String[] WEEK_DAYS = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
	private static String PATTERN_DATE = "yyyy-MM-dd";
	private static String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
	
	public static java.util.Date getDate(){
		return new java.util.Date();
	}
	public static java.sql.Date getSqlDate(){
		return new java.sql.Date(System.currentTimeMillis());
	}
	public static java.sql.Timestamp getTimestamp(){
		return new java.sql.Timestamp(System.currentTimeMillis());
	}
	public static java.util.Calendar getCalendar(){
		return java.util.Calendar.getInstance();
	}
	
	
	public static java.sql.Date parseSqlDate(String date){
		java.util.Date result = parseDate(date);
		return result==null?null:new java.sql.Date(result.getTime());
	}
	
	public static java.sql.Timestamp parseTimestamp(String date){
		java.util.Date result = parseDate(date);
		return result==null?null:new java.sql.Timestamp(result.getTime());
	}
	
	public static java.util.Calendar parseCalendar(String date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(parse(PATTERN_DATE, date));
		return calendar;
	}
	
	public static java.util.Date parseDate(String date){
		return new java.util.Date(parse(PATTERN_DATE, date));
	}
	
	public static java.util.Date parseDatetime(String date){
		return new java.util.Date(parse(PATTERN_DATETIME, date));
	}
	
	public static Object parse(Class clas, String date){
		 long t = parse(PATTERN_DATAS[date.length()] ,date);
		 if(java.util.Date.class.equals(clas)){
			 return new java.util.Date(t);
		 }else if(java.util.Calendar.class.equals(clas)){
			 java.util.Calendar c = java.util.Calendar.getInstance();
			 c.setTimeInMillis(t);
			 return c;
		 }else if(java.sql.Date.class.equals(clas)){
			 return new java.sql.Date(t);
		 }else if(java.sql.Timestamp.class.equals(clas)){
			 return new java.sql.Timestamp(t);
		 }
		 return null;
	}
	
	public static long parse(String pattern, String date){
		simpleDateFormat.applyPattern(pattern);
		try {
			return simpleDateFormat.parse(date).getTime();
		} catch (ParseException e) {
			return 0;
		}
	}
	
	
	//==============================
	public static String toDate(java.sql.Date date){
		return date==null?"":toDate(date.getTime());
	}
	
	public static String toDate(java.util.Calendar date){
		return date==null?"":toDate(date.getTime());
	}
	
	public static String toDate(java.util.Date date){
		return date==null?"":toDate(date.getTime());
	}
	
	public static String toDate(java.sql.Timestamp date){
		return date==null?"":toDate(date.getTime());
	}
	
	public static String toDate(long time){
		simpleDateFormat.applyPattern(PATTERN_DATE);
		return toDate(PATTERN_DATE, time);
	}
	
	public static String toDatetime(java.util.Calendar date){
		return date==null?"":toDatetime(date.getTime());
	}
	
	public static String toDatetime(java.util.Date date){
		return date==null?"":toDatetime(date.getTime());
	}
	
	public static String toDatetime(java.sql.Date date){
		return date==null?"":toDatetime(date.getTime());
	}
	
	public static String toDatetime(java.sql.Timestamp date){
		return date==null?"":toDatetime(date.getTime());
	}
	
	public static String toDatetime(long time){
		return toDate(PATTERN_DATETIME, time);
	}
	
	public static String toDate(String pattern,long time){
		simpleDateFormat.applyPattern(pattern);
		return simpleDateFormat.format(time);
	}
	
	/**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getCurrWeekDay() {
        return getCurrWeekDay(getDate());
    }
    
    public static String getCurrWeekDay(java.util.Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
 
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
 
        return WEEK_DAYS[w];
    }
}
