package login;

public class AdminBean {
	private String adminId;
	private String firstname;
	private String lastname;
	private String loginId;

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public AdminBean() {
		super();
	}

	public AdminBean(String adminId, String firstname, String lastname, String loginId) {
		super();
		this.adminId = adminId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.loginId = loginId;
	}

}
