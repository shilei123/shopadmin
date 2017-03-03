package framework.db.pojo;

import org.apache.struts2.json.annotations.JSON;

import framework.config.SysDict;

public class TXtEmp  implements java.io.Serializable{
	private String id;
	private String userId;
	private String userPwd;
	private String userName;
	private String orgId;
	private String positionId;
	private String workAddr;
	private String telphone;
	private String mobile;
	private String fax;
	private String sex;
	private String email;
	private String remark;
	private TXtOrg org;
	private TXtPosition position;
	private String orgPath;
	private String positionName;
	private String sexLabel;
	private String validateDomain;
	private String validateIp;
	private String pcusername;
	private String flag;
	
	public String getPcusername() {
		return pcusername;
	}
	public void setPcusername(String pcusername) {
		this.pcusername = pcusername;
	}
	public String getValidateDomain() {
		return validateDomain;
	}
	public void setValidateDomain(String validateDomain) {
		this.validateDomain = validateDomain;
	}
	public String getValidateIp() {
		return validateIp;
	}
	public void setValidateIp(String validateIp) {
		this.validateIp = validateIp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getWorkAddr() {
		return workAddr;
	}
	public void setWorkAddr(String workAddr) {
		this.workAddr = workAddr;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@JSON(serialize=false)
	public TXtOrg getOrg() {
		return org;
	}
	public void setOrg(TXtOrg org) {
		this.org = org;
	}
	@JSON(serialize=false)
	public TXtPosition getPosition() {
		return position;
	}
	public void setPosition(TXtPosition position) {
		this.position = position;
	}
	public String getOrgPath() {
		if(this.org != null) {
			orgPath = this.org.getOrgPath();
		}
		return orgPath;
	}
	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}
	public String getPositionName() {
		if(this.position != null) {
			positionName = this.position.getPositionName();
		}
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getSexLabel() {
		if(sex != null && !"".equals(sex)) {
			if(SysDict.SEX_MALE.equals(sex)) {
				sexLabel = "男";
			} else {
				sexLabel = "女";
			}
		}
		return sexLabel;
	}
	public void setSexLabel(String sexLabel) {
		this.sexLabel = sexLabel;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}