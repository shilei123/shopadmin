package demo.bankManager.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import demo.bankManager.pojo.TBankInfo;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("bankInfoDAO")
public class BankInfoDAO extends PageDAO{
	
	/**
	 * 根据名称查询
	 * @param pageBean
	 * @return pageData
	 */
	public final String SELECT_SQL = " select t.id,t.bank_name,t.url,t.tel,t.logo,t.bank_desc from t_bank_info t where flag=? ";
	
	@SuppressWarnings("unchecked")
	public List<TBankInfo> queryBankInfoPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add("1");
		String sql = this.buildWhereSql(pageBean, params);
		List<TBankInfo> pageData = this.query(sql, params, DBUtil.getInstance(), pageBean);
		return pageData;
	}
	
	public int queryBankInfoCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add("1");
		String sql = this.buildWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}
	
	private String buildWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(SELECT_SQL);
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String bankName = pageBean.getQueryParams().get("bankName");
			if (StringUtils.isNotBlank(bankName)){
				params.add("%"+bankName+"%");
				sql.append(" and t.bank_name like ? ");
			}
			String bankDesc = pageBean.getQueryParams().get("bankDesc");
			if (StringUtils.isNotBlank(bankDesc)){
				params.add("%"+bankDesc+"%");
				sql.append(" and t.bank_desc like ? ");
			}
		}
		sql.append(" order by t.create_time desc ");
		return sql.toString();
	}
	
	/**
	 *	获得银行信息 
	 */
	@SuppressWarnings("unchecked")
	public List<TBankInfo> getBankInfo() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flag", "1");
		return DBUtil.getInstance().queryByPojo(TBankInfo.class,params);
	}
}