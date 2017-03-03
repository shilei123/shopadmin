package framework.util.tos;


import java.sql.Date;

import framework.util.ToImp;


public class ToSqlDate implements ToImp {

	public Long parse(Object obj) {
		return ((Date)obj).getTime();
	}

	public Date to(Object obj) {
		return new Date((Long)obj);
	}

}
