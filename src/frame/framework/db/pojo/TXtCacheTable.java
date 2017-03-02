package framework.db.pojo;

import java.sql.Timestamp;

/**
 * TXtCacheTable entity. @author MyEclipse Persistence Tools
 */

public class TXtCacheTable implements java.io.Serializable {

	// Fields

	private String id;
	private String cacheType;
	private String tableName;
	private String querySql;
	private String createUserId;
	private String createOrgId;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public TXtCacheTable() {
	}

	/** minimal constructor */
	public TXtCacheTable(String id) {
		this.id = id;
	}

	/** full constructor */
	public TXtCacheTable(String id, String cacheType, String tableName,
			String querySql, String createUserId, String createOrgId, String flag,
			Timestamp createTime, Timestamp updateTime) {
		this.id = id;
		this.cacheType = cacheType;
		this.tableName = tableName;
		this.querySql = querySql;
		this.createUserId = createUserId;
		this.createOrgId = createOrgId;
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

	public String getCacheType() {
		return this.cacheType;
	}

	public void setCacheType(String cacheType) {
		this.cacheType = cacheType;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getQuerySql() {
		return this.querySql;
	}

	public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateOrgId() {
		return this.createOrgId;
	}

	public void setCreateOrgId(String createOrgId) {
		this.createOrgId = createOrgId;
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