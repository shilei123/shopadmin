package framework.util;

import java.util.Comparator;
import java.util.Map;

public class ComparatorOrgVO implements Comparator<Object> {

	public int compare(Object arg0, Object arg1) {
		Map item_0 = (Map) arg0;
		Map item_1 = (Map) arg1;
		int i1 = Integer.parseInt(((Map)item_1.get("attributes")).get("order").toString());
		int i0 = Integer.parseInt(((Map)item_0.get("attributes")).get("order").toString());
		if (i1 > i0) {
			return 0;
		} else {
			return 1;
		}
	}
}