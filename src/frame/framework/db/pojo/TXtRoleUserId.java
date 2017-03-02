package framework.db.pojo;

public class TXtRoleUserId implements java.io.Serializable {
	private String roleId;
	private String UId;

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUId() {
		return this.UId;
	}

	public void setUId(String UId) {
		this.UId = UId;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TXtRoleUserId))
			return false;
		TXtRoleUserId castOther = (TXtRoleUserId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this
				.getRoleId() != null && castOther.getRoleId() != null && this
				.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getUId() == castOther.getUId()) || (this.getUId() != null
						&& castOther.getUId() != null && this.getUId().equals(
						castOther.getUId())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result
				+ (getUId() == null ? 0 : this.getUId().hashCode());
		return result;
	}
}