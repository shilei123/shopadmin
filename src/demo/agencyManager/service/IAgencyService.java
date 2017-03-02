package demo.agencyManager.service;

import java.util.List;

import demo.agencyManager.bean.AgencyParamsBean;
import demo.agencyManager.pojo.TAgency;
import demo.agencyManager.pojo.TAgencyParams;

public interface IAgencyService {
	
	public void saveAgency(TAgency agency) throws Exception;
	
	public void saveAgencyPramas(AgencyParamsBean bean, TAgency agency) throws Exception;
	
	/**
	 * 根据agencyId查询子机构
	 * @param agencyId
	 * @return
	 * @throws Exception
	 */
	public List<TAgency> queryAgencyChilds(String agencyId) throws Exception;
	
	/**
	 * 根据agencyId查询机构
	 * @param agencyId
	 * @return
	 * @throws Exception
	 */
	public TAgency queryAgencyByAgencyId(String agencyId) throws Exception;
	
	/**
	 * 网体机构删除
	 * @param agencyId
	 * @return
	 * @throws Exception
	 */
	public int deleteAgency(String agencyId) throws Exception;
	
	/**
	 * 根据agencyId查询机构参数
	 * @param agencyId
	 * @return
	 * @throws Exception
	 */
	public List<TAgencyParams> queryAgencyParams(String agencyId) throws Exception;
	
}
