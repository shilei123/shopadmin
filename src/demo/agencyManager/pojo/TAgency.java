package demo.agencyManager.pojo;

import java.util.Date;

public class TAgency implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3579603764636029151L;
	private String id;
	private String agencyId;
	private String agencyName;
	private String shortName;
	private String parentAgencyId;
	private Integer order;
	private String root;
	private String agencyPath;
	private Integer agencyLevel;
	private String addr;
	private String initFlag;
	private String sts;
	private Date openTime;
	private Date closeTime;
	private String createUserId;
	private Date createTime;
	private String updateUserId;
	private Date updateTime;
	private String flag;

	public TAgency() {
	}


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgencyId() {
		return this.agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getAgencyName() {
		return this.agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getParentAgencyId() {
		return this.parentAgencyId;
	}

	public void setParentAgencyId(String parentAgencyId) {
		this.parentAgencyId = parentAgencyId;
	}

	public Integer getOrder() {
		return this.order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getRoot() {
		return this.root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getAgencyPath() {
		return this.agencyPath;
	}

	public void setAgencyPath(String agencyPath) {
		this.agencyPath = agencyPath;
	}

	public Integer getAgencyLevel() {
		return this.agencyLevel;
	}

	public void setAgencyLevel(Integer agencyLevel) {
		this.agencyLevel = agencyLevel;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getSts() {
		return this.sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public Date getOpenTime() {
		return this.openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getCloseTime() {
		return this.closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUserId() {
		return this.updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getInitFlag() {
		return initFlag;
	}

	public void setInitFlag(String initFlag) {
		this.initFlag = initFlag;
	}
}