package framework.util.json;

import framework.util.IJsonGetValue;

public class JsonGetValueString implements IJsonGetValue{

	public String getValue(Object obj) {
		return "\""+String.valueOf(obj).replaceAll("\"", "\\\\\"")+"\"";
	}

}
