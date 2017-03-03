package framework.db.pojo;

import java.sql.Timestamp;

/**
 * TXtGroupUser entity. @author MyEclipse Persistence Tools
 */

public class TXtGroupUser implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String userType;
	private String groupId;
	private String businessType;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public TXtGroupUser() {
	}

	/** minimal constructor */
	public TXtGroupUser(String id, String userId, String userType,
			String businessType, String flag, Timestamp createTime) {
		this.id = id;
		this.userId = userId;
		this.userType = userType;
		this.businessType = businessType;
		this.flag = flag;
		this.createTime = createTime;
	}

	/** full constructor */
	public TXtGroupUser(String id, String userId, String userType,
			String groupId, String businessType, String flag,
			Timestamp createTime, Timestamp updateTime) {
		this.id = id;
		this.userId = userId;
		this.userType = userType;
		this.groupId = groupId;
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

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
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