package demo.agencyManager.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import demo.agencyManager.pojo.TAgencyParams;
import framework.db.DBUtil;

@Repository("agencyParamsDAO")
public class AgencyParamsDAO {

	/**
	 *	@param agencyId/paramName
	 *	@return TAgencyParams 
	 */
	@SuppressWarnings("unchecked")
	public TAgencyParams findPojo(String agencyId, String paramName) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("agencyId", agencyId);
		params.put("paramName", paramName);
		params.put("flag", "1");
		List<TAgencyParams> list = DBUtil.getInstance().queryByPojo(TAgencyParams.class, params);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

}
