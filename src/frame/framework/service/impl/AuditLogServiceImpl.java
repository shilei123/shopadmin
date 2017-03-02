package framework.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import framework.bean.PageBean;
import framework.dao.AuditLogDAO;
import framework.db.pojo.TAuditLog;
import framework.service.IAuditLogService;

/**
 * 审计日志实现类.
 */
@Service("auditLogService")
public class AuditLogServiceImpl implements IAuditLogService {
	@Resource(name="auditLogDAO")
	private AuditLogDAO auditLogDAO;
	
	public boolean addAuditLog(TAuditLog log) throws Exception {
		Object obj = auditLogDAO.addAuditLog(log);
		return obj==null;
	}
	
	/**
	 * 查询日志
	 */
	public PageBean queryAllLogInfo(PageBean pageBean) throws Exception {
		// 拼接查询条件
		StringBuffer whereSql = new StringBuffer();
		if (pageBean.getQueryParams() != null
				&& !pageBean.getQueryParams().isEmpty()) {
			String loginname = pageBean.getQueryParams().get("loginname");
			if(StringUtils.isNotBlank(loginname)) {
				whereSql.append(" and t.loginname like '").append("%").append(loginname).append("%").append("'");
			}
			String beginDate = pageBean.getQueryParams().get("beginDate");
			if(StringUtils.isNotBlank(beginDate)) {
				whereSql.append(" and t.createdate >= '").append(beginDate).append("'");
			}			
			String endDate = pageBean.getQueryParams().get("endDate");
			if(StringUtils.isNotBlank(endDate)) {
				whereSql.append(" and t.createdate <= '").append(endDate).append("'");
			}
		}
		String orderbySql = " ORDER BY t.createdate DESC ";
		int total = auditLogDAO.queryLogCount(pageBean, whereSql.toString());
		List<TAuditLog> pageData = auditLogDAO.queryLogPagination(pageBean, whereSql.toString(), orderbySql);

		pageBean.setTotal(total);
		pageBean.setPageData(pageData);

		return pageBean;
	}
}
