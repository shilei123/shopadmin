package framework.util.tos;

import framework.util.ToImp;

public class ToChar implements ToImp{

	public Boolean parse(Object obj) {
		return Boolean.parseBoolean(String.valueOf(obj));
	}

	public Boolean to(Object obj) {
		return Boolean.parseBoolean(String.valueOf(obj));
	}

}
