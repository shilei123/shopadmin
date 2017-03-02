package framework.db.pojo;

import java.sql.Timestamp;

public class TXtRoleUser implements java.io.Serializable {

	private TXtRoleUserId id;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;

	public TXtRoleUserId getId() {
		return this.id;
	}

	public void setId(TXtRoleUserId id) {
		this.id = id;
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