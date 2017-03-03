package framework.db.pojo;

public class TXtOrg  implements java.io.Serializable{
	private String id;
	private String orgId;
	private String orgName;
	private String parentOrgId;
	private int order;
	private String root;
	private String shortName;
	private String orgPath;
	private int orgLevel;
	private String orgCode;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getParentOrgId() {
		return this.parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public int getOrder() {
		return this.order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getRoot() {
		return this.root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getOrgPath() {
		return this.orgPath;
	}

	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}
	
	public int getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(int orgLevel) {
		this.orgLevel = orgLevel;
	}
	
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
}