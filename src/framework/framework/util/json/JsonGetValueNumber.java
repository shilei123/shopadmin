package framework.util.json;

import framework.util.IJsonGetValue;

public class JsonGetValueNumber implements IJsonGetValue{

	public String getValue(Object obj) {
		return String.valueOf(obj);
	}

}
