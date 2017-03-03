package framework.db.pojo;

import java.sql.Timestamp;

public class TXtMenu implements java.io.Serializable {

	private String menuId;
	private String menuName;
	private String menuParentId;
	private String menuParentIds;
	private String url;
	private Integer order;
	private String openMethod;
	private String flag;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String logo;

	public TXtMenu() {
	}

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuParentId() {
		return this.menuParentId;
	}

	public void setMenuParentId(String menuParentId) {
		this.menuParentId = menuParentId;
	}

	public String getMenuParentIds() {
		return this.menuParentIds;
	}

	public void setMenuParentIds(String menuParentIds) {
		this.menuParentIds = menuParentIds;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOpenMethod() {
		return openMethod;
	}

	public void setOpenMethod(String openMethod) {
		this.openMethod = openMethod;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
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