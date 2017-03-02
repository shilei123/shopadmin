package framework.util.tos;

import framework.util.ToImp;

public class ToLong implements ToImp{

	public Long parse(Object obj) {
		return Long.parseLong(String.valueOf(obj));
	}

	public Long to(Object obj) {
		return Long.parseLong(String.valueOf(obj));
	}

}
