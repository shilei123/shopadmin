package framework.util.tos;

import java.util.Date;

import framework.util.ToImp;


public class ToDate implements ToImp {

	public Long parse(Object obj) {
		return ((Date)obj).getTime();
	}

	public Date to(Object obj) {
		return new Date((Long)obj);
	}

}
