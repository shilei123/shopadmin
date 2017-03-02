package demo.agencyManager.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import demo.agencyManager.pojo.TAgency;
import framework.db.DBUtil;

@SuppressWarnings({"unchecked"})
@Repository("agencyDAO")
public class AgencyDAO {
	private static final String MAX_AGENCY_ID = " select max(o.agency_id) max_agency_id from t_agency o where o.parent_agency_id=? ";
	
	public String getMaxAgencyId(DBUtil db, String parentAgencyId) {
		List<Map<String,String>> list = db.queryBySQL(MAX_AGENCY_ID, parentAgencyId);
		if(list!=null && !list.isEmpty()) {
			Map<String,String> map = list.get(0);
			String maxAgencyId = map.get("maxAgencyId");
			if(StringUtils.isNotBlank(maxAgencyId)) {
				int len = maxAgencyId.length();
				int temp = Integer.parseInt(maxAgencyId);
				temp += 1;
				String newMaxAgencyId = String.valueOf(temp);
				if(newMaxAgencyId.length() < len) {
					int diffLen = len - newMaxAgencyId.length();
					StringBuffer addZreo = new StringBuffer();
					for(int i = 0; i < diffLen; i++) {
						addZreo.append("0");
					}
					newMaxAgencyId = addZreo.toString() + newMaxAgencyId;
				}
				return newMaxAgencyId;
			}
		}
		return new StringBuffer(parentAgencyId).append("001").toString();
	}
	
	public List<TAgency> queryAgency() {
		Map<String, Object> params = new HashMap<String, Object>(1);
		params.put("flag", "1");
		return DBUtil.getInstance().queryByPojo(TAgency.class, params);
	}
}
