package enums;

public enum AdminEnums {
	ERROR_ADMIN_MSG("ERROR_ADMIN_MSG"), ERROR_STUDENT("ERROR_STUDENT"), USER_ADMIN("USER_ADMIN"), SUBJECTS("SUBJECTS"), EMAIL(
			"email"), PASSWORD("password"), LOGIN_ID("loginid"), SESSION_USER("SESS_USER"), SESSION_START_TIME("SESS_LOGIN_TIME"),
	IS_SESSION_VALID("SESS_VALID");
	

	private String adminEnum;

	private AdminEnums(final String adminEnum) {
		this.adminEnum = adminEnum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.adminEnum;
	}

}
