package framework.db.pojo;

import java.sql.Timestamp;

/**
 * TPbDatasource entity. @author MyEclipse Persistence Tools
 */

public class TPbDatasource implements java.io.Serializable {

	// Fields

	private String id;
	private String sourceName;
	private String sourceRemark;
	private String sourceSql;
	private String createUserId;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String whereSql;

	// Constructors

	/** default constructor */
	public TPbDatasource() {
	}

	/** minimal constructor */
	public TPbDatasource(String id, String createUserId, Timestamp createTime) {
		this.id = id;
		this.createUserId = createUserId;
		this.createTime = createTime;
	}

	/** full constructor */
	public TPbDatasource(String id, String sourceName, String sourceRemark,
			String sourceSql, String createUserId, String flag,
			Timestamp createTime, Timestamp updateTime) {
		this.id = id;
		this.sourceName = sourceName;
		this.sourceRemark = sourceRemark;
		this.sourceSql = sourceSql;
		this.createUserId = createUserId;
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

	public String getSourceName() {
		return this.sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceRemark() {
		return this.sourceRemark;
	}

	public void setSourceRemark(String sourceRemark) {
		this.sourceRemark = sourceRemark;
	}

	public String getSourceSql() {
		return this.sourceSql;
	}

	public void setSourceSql(String sourceSql) {
		this.sourceSql = sourceSql;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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

	public String getWhereSql() {
		return whereSql;
	}

	public void setWhereSql(String whereSql) {
		this.whereSql = whereSql;
	}
	
}