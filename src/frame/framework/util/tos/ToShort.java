package framework.util.tos;

import framework.util.ToImp;

public class ToShort implements ToImp{

	public Short parse(Object obj) {
		return Short.parseShort(String.valueOf(obj));
	}

	public Short to(Object obj) {
		return Short.parseShort(String.valueOf(obj));
	}

}
