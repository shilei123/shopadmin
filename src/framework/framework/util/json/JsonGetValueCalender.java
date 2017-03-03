package framework.util.json;

import java.util.Calendar;

public class JsonGetValueCalender extends JsonGetValueDateNumber{
	public JsonGetValueCalender(){
		super();
	}
	
	@Override
	public String getValue(Object obj) {
		return super.getValue(((Calendar)obj).getTime());
	}

}
