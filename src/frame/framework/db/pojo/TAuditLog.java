package framework.db.pojo;

import java.util.Date;

/**
 * 审计日志.
 * @author yangchaowen
 */
@SuppressWarnings("serial")
public class TAuditLog implements java.io.Serializable {
	private String id;//日志ID
	private String loginname;//登录名称
	private String logdetail;//登录详情
	private String logsource;//日志源，对应类路径以及方法.
	private String ip;//IP地址
	private Date createdate;//创建日期
    private Date startdate;//起始时间
    private Date enddate;//终止时间
    
    public TAuditLog() {
	}
	
	public TAuditLog(String loginName,String logDetail) {
		this.loginname = loginName;
		this.logdetail = logDetail;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLogdetail() {
		return logdetail;
	}

	public void setLogdetail(String logdetail) {
		this.logdetail = logdetail;
	}

	public String getLogsource() {
		return logsource;
	}

	public void setLogsource(String logsource) {
		this.logsource = logsource;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
}