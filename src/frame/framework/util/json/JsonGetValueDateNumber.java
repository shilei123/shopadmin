package framework.util.json;

import java.text.SimpleDateFormat;

public class JsonGetValueDateNumber extends JsonGetValueString{
	public final static SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
	public final static SimpleDateFormat FORMAT_DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private SimpleDateFormat formet;

	public JsonGetValueDateNumber(){
		formet = FORMAT_DATE_TIME;
	}
	
	public SimpleDateFormat getFormet() {
		return formet;
	}

	public void setFormet(SimpleDateFormat formet) {
		this.formet = formet;
	}

	@Override
	public String getValue(Object obj) {
		return super.getValue(formet.format(obj));
	}

}
