package framework.util.tos;

import framework.util.ToImp;

public class ToInt implements ToImp{

	public Integer parse(Object obj) {
		return Integer.parseInt(String.valueOf(obj));
	}

	public Integer to(Object obj) {
		return Integer.parseInt(String.valueOf(obj));
	}

}
