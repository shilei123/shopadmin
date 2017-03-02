package framework.util.tos;

import framework.util.ToImp;

public class ToFloat implements ToImp{

	public Float parse(Object obj) {
		return Float.parseFloat(String.valueOf(obj));
	}

	public Float to(Object obj) {
		return Float.parseFloat(String.valueOf(obj));
	}

}
