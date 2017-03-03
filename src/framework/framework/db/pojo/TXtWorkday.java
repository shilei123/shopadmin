package framework.db.pojo;

import java.sql.Timestamp;

/**
 * TXtWorkday entity. @author MyEclipse Persistence Tools
 */

public class TXtWorkday implements java.io.Serializable {

	// Fields

	private String day;
	private String workday;
	private String remark;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public TXtWorkday() {
	}

	/** minimal constructor */
	public TXtWorkday(String day) {
		this.day = day;
	}

	/** full constructor */
	public TXtWorkday(String day, String workday, String remark, String flag,
			Timestamp createTime, Timestamp updateTime) {
		this.day = day;
		this.workday = workday;
		this.remark = remark;
		this.flag = flag;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	// Property accessors

	public String getDay() {
		return this.day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getWorkday() {
		return this.workday;
	}

	public void setWorkday(String workday) {
		this.workday = workday;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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