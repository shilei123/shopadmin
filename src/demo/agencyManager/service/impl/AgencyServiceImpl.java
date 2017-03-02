package demo.agencyManager.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import demo.agencyManager.bean.AgencyParamsBean;
import demo.agencyManager.dao.AgencyDAO;
import demo.agencyManager.pojo.TAgency;
import demo.agencyManager.pojo.TAgencyParams;
import demo.agencyManager.service.IAgencyService;

import framework.bean.UserMsg;
import framework.config.SysDict;
import framework.db.DBUtil;
import framework.helper.RequestHelper;

@SuppressWarnings({"rawtypes","unchecked"})
@Service("agencyService")
public class AgencyServiceImpl implements IAgencyService {
	@Resource(name="agencyDAO")
	private AgencyDAO agencyDAO;
	
	public void saveAgency(TAgency agency) throws Exception {
		DBUtil db = DBUtil.getInstance();
		if(agency.getId() == null || "".equals(agency.getId())) {
			TAgency parentAgency = this.getParentAgency(db, agency);
			if(parentAgency != null) {
				String parentAgencyId = parentAgency.getAgencyId();
				String maxAgencyId = this.agencyDAO.getMaxAgencyId(db, parentAgencyId);
				agency.setId(UUID.randomUUID().toString());
				agency.setAgencyId(maxAgencyId);
				agency.setRoot(null);
				agency.setAgencyPath(parentAgency.getAgencyPath() + "/" + agency.getAgencyName());
				agency.setAgencyLevel(2);
				agency.setRoot("0");
				agency.setSts(SysDict.FLAG_ACT); //机构默认为停用状态
				agency.setInitFlag(SysDict.FLAG_HIS); //机构默认为未初始化状态
				db.insert(agency);
				
				List<TAgencyParams> copyList = this.copyAgencyParams(parentAgencyId, maxAgencyId);
				if(copyList != null && !copyList.isEmpty()) {
					db.insert(copyList);
				}
			}
		} else {
			Map params = new HashMap(1);
			params.put("id", agency.getId());
			TAgency vo = (TAgency) db.queryByPojo(TAgency.class, params).get(0);
			vo.setAgencyName(agency.getAgencyName());
			vo.setShortName(agency.getShortName());
			vo.setOrder(agency.getOrder());
			db.update(vo);
		}
	}
	
	private TAgency getParentAgency(DBUtil db, TAgency agency) {
		Map<String, Object> params = new HashMap<String, Object>(1);
		params.put("agencyId", agency.getParentAgencyId());
		List<TAgency> list = db.queryByPojo(TAgency.class, params);
		if(list != null && !list.isEmpty()) {
			TAgency ni = list.get(0);
			return ni;
		}
		return null;
	}
	
	private List<TAgencyParams> copyAgencyParams(String fromAgencyId, String toAgencyId) throws Exception {
		List<TAgencyParams> list = this.queryAgencyParams(fromAgencyId);
		List<TAgencyParams> copyList = null;
		if(list != null && !list.isEmpty()) {
			copyList = new ArrayList<TAgencyParams>(list.size());
			UserMsg user = (UserMsg) RequestHelper.getSession().getAttribute("user");
			for (TAgencyParams param : list) {
				TAgencyParams newParam = new TAgencyParams();
				newParam.setId(UUID.randomUUID().toString());
				newParam.setAgencyId(toAgencyId);
				newParam.setParamVal(param.getParamVal());
				newParam.setParamDesc(param.getParamDesc());
				newParam.setParamName(param.getParamName());
				newParam.setTitle(param.getTitle());
				newParam.setCreateTime(new Date());
				newParam.setCreateUserId(user.getUId());
				newParam.setOrder(param.getOrder());
				copyList.add(newParam);
			}
		}
		return copyList;
	}
	
	/**
	 * 根据agencyId查询子机构
	 * @param agencyId
	 * @return
	 * @throws Exception
	 */
	public List<TAgency> queryAgencyChilds(String agencyId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("flag", SysDict.FLAG_ACT);
		params.put("parentAgencyId", agencyId);
		return DBUtil.getInstance().queryByPojo(TAgency.class, params);
	}

	/**
	 * 根据agencyId查询机构
	 * @param agencyId
	 * @return
	 * @throws Exception
	 */
	public TAgency queryAgencyByAgencyId(String agencyId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("flag", SysDict.FLAG_ACT);
		params.put("agencyId", agencyId);
		List<TAgency> list = DBUtil.getInstance().queryByPojo(TAgency.class, params);
		if(list!=null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 网体机构删除
	 * @param agencyId
	 * @return
	 * @throws Exception
	 */
	public int deleteAgency(String id) throws Exception {
		DBUtil db = DBUtil.getInstance();
		String hql = " update TAgency set flag=? where id=? ";
		int count = db.executeHql(hql, SysDict.FLAG_HIS, id);
		//db.executeSQL(deleteMenuSql, SysDict.FLAG_HIS, agencyId);
		//db.commit();
		return count;
	}

	/**
	 * 根据agencyId查询机构参数
	 * @param agencyId
	 * @return
	 * @throws Exception
	 */
	public List<TAgencyParams> queryAgencyParams(String agencyId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("flag", SysDict.FLAG_ACT);
		params.put("agencyId", agencyId);
		return DBUtil.getInstance().queryByPojo(TAgencyParams.class, params, " order asc ");
	}

	public void saveAgencyPramas(AgencyParamsBean bean, TAgency agency) throws Exception {
		DBUtil db = DBUtil.getInstance();
		Field[] fields = bean.getClass().getDeclaredFields(); //得到类中的所有属性集合
		for (Field field : fields) {
			field.setAccessible(true); //设置些属性是可以访问的  
			Object paramVal = field.get(bean);//得到此属性的值
			String paramName = field.getName();
			db.executeSQL("update t_agency_params set param_val=? where param_name=? and agency_id=? ", paramVal, paramName, agency.getAgencyId());
		}
	}

}