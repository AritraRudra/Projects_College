package login;

public class AdminBean
{
	private String adminid;
	private String fstname;
	private String lstname;
	private String loginid;
	public String getAdminid() {
		return adminid;
	}
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	public String getFstname() {
		return fstname;
	}
	public void setFstname(String fstname) {
		this.fstname = fstname;
	}
	public String getLstname() {
		return lstname;
	}
	public void setLstname(String lstname) {
		this.lstname = lstname;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public AdminBean(String adminid, String fstname, String lstname,
			String loginid) {
		super();
		this.adminid = adminid;
		this.fstname = fstname;
		this.lstname = lstname;
		this.loginid = loginid;
	}
	public AdminBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
