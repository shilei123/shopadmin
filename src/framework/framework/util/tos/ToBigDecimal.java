package framework.util.tos;

import java.math.BigDecimal;

import framework.util.ToImp;


public class ToBigDecimal implements ToImp{

	public String parse(Object obj) {
		return String.valueOf(obj);
	}

	public BigDecimal to(Object obj) {
		return new BigDecimal(String.valueOf(obj));
	}

}
