package framework.db.pojo;

import java.sql.Timestamp;

public class TXtRole implements java.io.Serializable {

	private String roleId;
	private String roleName;
	private String roleType;
	private String roleRemark;
	private String roleStateValue;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;

	public TXtRole() {
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleType() {
		return this.roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleRemark() {
		return this.roleRemark;
	}

	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}

	public String getRoleStateValue() {
		return this.roleStateValue;
	}

	public void setRoleStateValue(String roleStateValue) {
		this.roleStateValue = roleStateValue;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
}