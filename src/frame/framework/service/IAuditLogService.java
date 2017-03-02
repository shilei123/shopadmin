package framework.service;

import framework.bean.PageBean;
import framework.db.pojo.TAuditLog;

/**
 * 审计日志服务接口。
 */
public interface IAuditLogService {

	/**
	 * 添加日志信息.
	 * @param log
	 * @return
	 */
	public boolean addAuditLog(TAuditLog log) throws Exception;

	public PageBean queryAllLogInfo(PageBean pageBean) throws Exception;
}
