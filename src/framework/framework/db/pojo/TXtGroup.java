package framework.db.pojo;

import java.sql.Timestamp;

/**
 * TXtGroup entity. @author MyEclipse Persistence Tools
 */

public class TXtGroup implements java.io.Serializable {

	// Fields

	private String id;
	private String groupName;
	private String businessType;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public TXtGroup() {
	}

	/** minimal constructor */
	public TXtGroup(String id, String groupName, String businessType,
			String flag, Timestamp createTime) {
		this.id = id;
		this.groupName = groupName;
		this.businessType = businessType;
		this.flag = flag;
		this.createTime = createTime;
	}

	/** full constructor */
	public TXtGroup(String id, String groupName, String businessType,
			String flag, Timestamp createTime, Timestamp updateTime) {
		this.id = id;
		this.groupName = groupName;
		this.businessType = businessType;
		this.flag = flag;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}