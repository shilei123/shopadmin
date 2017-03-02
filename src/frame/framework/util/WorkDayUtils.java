package framework.util;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import framework.db.DBUtil;

@SuppressWarnings("rawtypes")
@Component
public class WorkDayUtils {
	
	public String getNextWorkDay(String day, int num) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select x1.days from ( ");
		sql.append(" select rownum rown,x.* from ( ");
		sql.append(" select to_number(replace(t.day,'-','')) days from t_xt_workday t where t.workday='1'  ");
		sql.append(" ) x where x.days>=to_number(replace(?,'-','')) and rownum<=? ");
		sql.append(" ) x1 where x1.rown>=? ");
		
		DBUtil db = DBUtil.getInstance();
		List list = db.queryBySQL(sql.toString(), day, num, num);
		if(list!=null && !list.isEmpty()) {
			Map map = (Map)list.get(0);
			return (map.get("days")==null?null:map.get("days").toString());
		}
		return null;
	}
}
