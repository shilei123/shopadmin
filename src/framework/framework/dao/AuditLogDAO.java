package framework.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;
import framework.db.pojo.TAuditLog;

@Repository("auditLogDAO")
public class AuditLogDAO extends PageDAO {
	
	private final String T_XT_AUDIT_LOG_SELECT = "select t.id,t.loginname,"
			+ "t.logdetail,t.logsource,t.ip,t.createdate from t_xt_audit_log t where 1=1 ";
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	/**
	 * 添加日志.
	 * @param log
	 * @return
	 */
	public Object addAuditLog(TAuditLog log) throws Exception{
		log.setCreatedate(new Date());
		log.setId(UUID.randomUUID().toString());
		return hibernateTemplate.save(log);
	}
	
	/**
	 * 分页查询日志
	 * @param pageBean
	 * @param whereSql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TAuditLog> queryLogPagination(PageBean pageBean, String whereSql,String orderbySql) {
		List<TAuditLog> pageData = this.query(T_XT_AUDIT_LOG_SELECT + whereSql + orderbySql, null, DBUtil.getInstance(), pageBean);
		return pageData;
	}
	/**
	 * 统计日志
	 * @param pageBean
	 * @param whereSql
	 * @return
	 */
	public int queryLogCount(PageBean pageBean, String whereSql) {
		return DBUtil.getInstance().queryCountBySQL(T_XT_AUDIT_LOG_SELECT + whereSql, null);
	}
}
