package framework.db.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * TXtMessage entity. @author MyEclipse Persistence Tools
 */

public class TXtMessage implements java.io.Serializable {

	// Fields

	private String id;
	private String batchId;
	private String replyMessageId;
	private String toUserId;
	private String title;
	private String message;
	private String messageType;
	private BigDecimal messageStatus;
	private BigDecimal messageLevel;
	private String url;
	private String createUserId;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public TXtMessage() {
	}

	/** minimal constructor */
	public TXtMessage(String id, String toUserId, String title, String message,
			String messageType, BigDecimal messageStatus,
			BigDecimal messageLevel, String createUserId, Timestamp createTime,
			Timestamp updateTime) {
		this.id = id;
		this.toUserId = toUserId;
		this.title = title;
		this.message = message;
		this.messageType = messageType;
		this.messageStatus = messageStatus;
		this.messageLevel = messageLevel;
		this.createUserId = createUserId;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	/** full constructor */
	public TXtMessage(String id, String batchId, String replyMessageId,
			String toUserId, String title, String message, String messageType,
			BigDecimal messageStatus, BigDecimal messageLevel, String url,
			String createUserId, String flag, Timestamp createTime,
			Timestamp updateTime) {
		this.id = id;
		this.batchId = batchId;
		this.replyMessageId = replyMessageId;
		this.toUserId = toUserId;
		this.title = title;
		this.message = message;
		this.messageType = messageType;
		this.messageStatus = messageStatus;
		this.messageLevel = messageLevel;
		this.url = url;
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

	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getReplyMessageId() {
		return this.replyMessageId;
	}

	public void setReplyMessageId(String replyMessageId) {
		this.replyMessageId = replyMessageId;
	}

	public String getToUserId() {
		return this.toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageType() {
		return this.messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public BigDecimal getMessageStatus() {
		return this.messageStatus;
	}

	public void setMessageStatus(BigDecimal messageStatus) {
		this.messageStatus = messageStatus;
	}

	public BigDecimal getMessageLevel() {
		return this.messageLevel;
	}

	public void setMessageLevel(BigDecimal messageLevel) {
		this.messageLevel = messageLevel;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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

}