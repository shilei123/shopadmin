package framework.db.pojo;

import java.sql.Timestamp;

public class TXtRoleMenu implements java.io.Serializable {

	private TXtRoleMenuId id;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;

	public TXtRoleMenu() {
	}
	
	public TXtRoleMenu(TXtRoleMenuId id, Timestamp createTime) {
		this.id = id;
		this.createTime = createTime;
	}

	public TXtRoleMenu(TXtRoleMenuId id, String flag, Timestamp createTime,
			Timestamp updateTime) {
		this.id = id;
		this.flag = flag;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public TXtRoleMenuId getId() {
		return this.id;
	}

	public void setId(TXtRoleMenuId id) {
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