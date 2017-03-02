package framework.util.tos;


import java.util.Calendar;

import framework.util.ToImp;


public class ToCalendar implements ToImp {

	public Long parse(Object obj) {
		return ((Calendar)obj).getTimeInMillis();
	}

	public Object to(Object obj) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis((Long)obj);
		return c;
	}

}
