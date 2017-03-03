package framework.util.json;

import java.util.Date;

public class JsonGetValueDate extends JsonGetValueDateNumber{
	public JsonGetValueDate(){
		super();
	}
	
	@Override
	public String getValue(Object obj) {
		return super.getValue(((Date)obj).getTime());
	}

}
