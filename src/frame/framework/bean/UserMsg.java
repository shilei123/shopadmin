package framework.bean;

import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;

import framework.db.pojo.TXtUser;

public class UserMsg extends TXtUser {
	private String orgId;//机构主键Id
	private String orgOrgId; //对应数据库的orgId列 
	private String orgName;//机构名称
	private String orgShortName;//机构简称
	private String orgPath;//机构路径
	private String deptId;//部门Id
	private String deptName;//部门名称
	private String deptShortName;//部门简称
	private String orgCode; //单位代号
	private int orgLevel; //直属的机构等级
	private Map<String,String> role;//角色
	private String parentOrgRowId;//上级机构主键ID
	private String parentOrgId;//上级机构代码
	private String parentOrgName;//上级机构名称	
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgOrgId() {
		return orgOrgId;
	}
	public void setOrgOrgId(String orgOrgId) {
		this.orgOrgId = orgOrgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Map<String, String> getRole() {
		return role;
	}
	public void setRole(Map<String, String> role) {
		this.role = role;
	}
	public String getOrgPath() {
		return orgPath;
	}
	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}
	
	public String getParentOrgRowId() {
		return parentOrgRowId;
	}
	public void setParentOrgRowId(String parentOrgRowId) {
		this.parentOrgRowId = parentOrgRowId;
	}
	public String getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	public String getParentOrgName() {
		return parentOrgName;
	}
	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}
	public String toRoleString() {
		StringBuffer sb = new StringBuffer();
		if(this.role != null && !role.isEmpty()) {
			String[] set = role.keySet().toArray(new String[1]);
			
			for (String s : set) {
				sb.append(",'").append(s).append("'");
			}
		}
		if(sb.length() > 0 && sb.toString().startsWith(",")) {
			return sb.toString().replaceFirst(",", "");
		}
		return "'0'";
	}
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public int getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(int orgLevel) {
		this.orgLevel = orgLevel;
	}
	public String getOrgShortName() {
		if(StringUtils.isBlank(orgShortName)) {
			return this.orgName;
		}
		return orgShortName;
	}
	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}
	public String getDeptShortName() {
		if(StringUtils.isBlank(deptShortName)) {
			return this.deptName;
		}
		return deptShortName;
	}
	public void setDeptShortName(String deptShortName) {
		this.deptShortName = deptShortName;
	}
}