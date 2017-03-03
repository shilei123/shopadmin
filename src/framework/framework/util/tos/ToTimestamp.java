package framework.util.tos;

import java.sql.Timestamp;
import java.util.Date;

import framework.util.ToImp;


public class ToTimestamp implements ToImp {

	public Long parse(Object obj) {
		return ((Timestamp)obj).getTime();
	}

	public Date to(Object obj) {
		return new Timestamp((Long)obj);
	}

}
