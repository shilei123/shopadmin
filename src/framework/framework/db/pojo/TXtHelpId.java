package framework.db.pojo;

/**
 * TXtHelpId entity. @author MyEclipse Persistence Tools
 */

public class TXtHelpId implements java.io.Serializable {

	// Fields

	private String menuId;
	private String userType;

	// Constructors

	/** default constructor */
	public TXtHelpId() {
	}

	/** full constructor */
	public TXtHelpId(String menuId, String userType) {
		this.menuId = menuId;
		this.userType = userType;
	}

	// Property accessors

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TXtHelpId))
			return false;
		TXtHelpId castOther = (TXtHelpId) other;

		return ((this.getMenuId() == castOther.getMenuId()) || (this
				.getMenuId() != null
				&& castOther.getMenuId() != null && this.getMenuId().equals(
				castOther.getMenuId())))
				&& ((this.getUserType() == castOther.getUserType()) || (this
						.getUserType() != null
						&& castOther.getUserType() != null && this
						.getUserType().equals(castOther.getUserType())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getMenuId() == null ? 0 : this.getMenuId().hashCode());
		result = 37 * result
				+ (getUserType() == null ? 0 : this.getUserType().hashCode());
		return result;
	}

}