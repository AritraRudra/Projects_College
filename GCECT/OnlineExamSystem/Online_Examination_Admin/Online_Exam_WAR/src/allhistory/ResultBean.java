package allhistory;

import java.util.Date;

public class ResultBean 
{
	private int exam_id;
	private int stud_id;
	private String uname;
	private String subject;
	private String level;
	private int marks;
	Date date_time;
	public ResultBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResultBean(int examId, int studId, String uname, String subject,
			String level, int marks, Date dateTime) {
		super();
		exam_id = examId;
		stud_id = studId;
		this.uname = uname;
		this.subject = subject;
		this.level = level;
		this.marks = marks;
		date_time = dateTime;
	}
	public int getExam_id() {
		return exam_id;
	}
	public void setExam_id(int examId) {
		exam_id = examId;
	}
	public int getStud_id() {
		return stud_id;
	}
	public void setStud_id(int studId) {
		stud_id = studId;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public Date getDate_time() {
		return date_time;
	}
	public void setDate_time(Date dateTime) {
		date_time = dateTime;
	}
	
	
}
