package framework.util.tos;

import framework.util.ToImp;

public class ToBoolean implements ToImp{

	public Character parse(Object obj) {
		return obj.toString().charAt(0);
	}

	public Character to(Object obj) {
		return obj.toString().charAt(0);
	}

}
