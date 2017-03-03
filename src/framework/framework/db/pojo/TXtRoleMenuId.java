package framework.db.pojo;

/**
 * TXtRoleMenuId entity. @author MyEclipse Persistence Tools
 */

public class TXtRoleMenuId implements java.io.Serializable {

	// Fields

	private String roleId;
	private String menuId;

	// Constructors

	/** default constructor */
	public TXtRoleMenuId() {
	}

	/** full constructor */
	public TXtRoleMenuId(String roleId, String menuId) {
		this.roleId = roleId;
		this.menuId = menuId;
	}

	// Property accessors

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TXtRoleMenuId))
			return false;
		TXtRoleMenuId castOther = (TXtRoleMenuId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this
				.getRoleId() != null
				&& castOther.getRoleId() != null && this.getRoleId().equals(
				castOther.getRoleId())))
				&& ((this.getMenuId() == castOther.getMenuId()) || (this
						.getMenuId() != null
						&& castOther.getMenuId() != null && this.getMenuId()
						.equals(castOther.getMenuId())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result
				+ (getMenuId() == null ? 0 : this.getMenuId().hashCode());
		return result;
	}

}