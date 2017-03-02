package framework.util.tos;

import framework.util.ToImp;

public class ToString implements ToImp {

	public Object parse(Object obj) {
		return String.valueOf(obj);
	}

	public Object to(Object obj) {
		return String.valueOf(obj);
	}

}
