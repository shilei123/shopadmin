package framework.db.pojo;

import java.sql.Timestamp;

public class TPbUploadfile implements java.io.Serializable {

	private String fileId;
	private Integer parentFileId;
	private String fileName;
	private String fileType;
	private Integer fileSize;
	private String filePath;
	private String upType;
	private String createUserId;
	private String userType;
	private String downScope;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String groupId;


	public TPbUploadfile() {
	}

	public TPbUploadfile(String fileId, String fileName, Integer fileSize,
			String filePath, String createUserId, String userType, String flag,
			Timestamp createTime, String groupId) {
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.filePath = filePath;
		this.createUserId = createUserId;
		this.userType = userType;
		this.flag = flag;
		this.createTime = createTime;
		this.groupId = groupId;
	}

	public TPbUploadfile(String fileId, Integer parentFileId, String fileName,
			String fileType, Integer fileSize, String filePath, String upType,
			String createUserId, String userType, String downScope, String flag,
			Timestamp createTime, Timestamp updateTime,String groupId) {
		this.fileId = fileId;
		this.parentFileId = parentFileId;
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.filePath = filePath;
		this.upType = upType;
		this.createUserId = createUserId;
		this.userType = userType;
		this.downScope = downScope;
		this.flag = flag;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.groupId = groupId;
	}

	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Integer getParentFileId() {
		return this.parentFileId;
	}

	public void setParentFileId(Integer parentFileId) {
		this.parentFileId = parentFileId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getUpType() {
		return this.upType;
	}

	public void setUpType(String upType) {
		this.upType = upType;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getDownScope() {
		return downScope;
	}

	public void setDownScope(String downScope) {
		this.downScope = downScope;
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