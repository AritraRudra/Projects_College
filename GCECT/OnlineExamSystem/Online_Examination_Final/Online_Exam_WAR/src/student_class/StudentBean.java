package student_class;

public class StudentBean {
	private String uname;
	private String fstname;
	private String lstname;
	private String email;
	private String passwd;
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public StudentBean() {
		super();
	}
	
	public StudentBean(String uname, String fstname, String lstname,
			String email, String passwd) {
		super();
		this.uname = uname;
		this.fstname = fstname;
		this.lstname = lstname;
		this.email = email;
		this.passwd = passwd;
	}
	
}
