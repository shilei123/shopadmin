package framework.util.json;

import java.util.Date;

public class JsonGetValueSqlDate extends JsonGetValueDateNumber{
	public JsonGetValueSqlDate(){
		super();
	}
	
	@Override
	public String getValue(Object obj) {
		return super.getValue(((Date)obj).getTime());
	}

}
