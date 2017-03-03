package framework.db.pojo;

import java.sql.Timestamp;

public class TXtUser implements java.io.Serializable {
	private String UId;
	private String UPwd;
	private String UName;
	private String URemark;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String order;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getUId() {
		return this.UId;
	}

	public void setUId(String UId) {
		this.UId = UId;
	}

	public String getUPwd() {
		return this.UPwd;
	}

	public void setUPwd(String UPwd) {
		this.UPwd = UPwd;
	}

	public String getUName() {
		return this.UName;
	}

	public void setUName(String UName) {
		this.UName = UName;
	}

	public String getURemark() {
		return this.URemark;
	}

	public void setURemark(String URemark) {
		this.URemark = URemark;
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