package framework.action.log;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;

import framework.action.PageAction;
import framework.bean.PageBean;
import framework.bean.UserMsg;
import framework.db.pojo.TAuditLog;
import framework.helper.RequestHelper;
import framework.logger.AuditLogger;
import framework.service.IAuditLogService;

public class AuditLogAction extends PageAction {

	@Resource(name="auditLogService")
	private IAuditLogService auditLogService;
	private TAuditLog message;
	private AuditLogger logger = AuditLogger.getLogger();
	UserMsg user = (UserMsg)RequestHelper.getSession().getAttribute("user");
	
	/**
	 * 日志浏览
	 * @return
	 */
	public String queryLog() {
		try {
			PageBean resultData = auditLogService.queryAllLogInfo(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			/*e.printStackTrace();*/
			message = new TAuditLog(user.getUId(), "查询日志列表失败！");
			logger.info(message);
		}
		return Action.SUCCESS; 
	}
	
}