package framework.util;

import java.util.Comparator;
import java.util.Map;

public class ComparatorFaqTypeVO implements Comparator<Object> {

	@SuppressWarnings("rawtypes")
	public int compare(Object arg0, Object arg1) {
		Map item_0 = (Map) arg0;
		Map item_1 = (Map) arg1;
		Integer i0 = Integer.parseInt(((Map)item_0.get("attributes")).get("typeOrder").toString());
		Integer i1 = Integer.parseInt(((Map)item_1.get("attributes")).get("typeOrder").toString());
		return i0.compareTo(i1);
	}
}