package login;

public class AdminBean {
	private String userId;
	private String firstname;
	private String lastname;
	private String loginId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public AdminBean(String userId, String firstname, String lastname, String loginId) {
		super();
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.loginId = loginId;
	}

}
