package framework.util.tos;

import framework.util.ToImp;

public class ToDouble implements ToImp{

	public Double parse(Object obj) {
		return Double.parseDouble(String.valueOf(obj));
	}

	public Double to(Object obj) {
		return Double.parseDouble(String.valueOf(obj));
	}

}
