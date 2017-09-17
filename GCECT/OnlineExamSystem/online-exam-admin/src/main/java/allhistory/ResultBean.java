package allhistory;

import java.time.LocalDateTime;

public class ResultBean {
	private int examId;
	private int studentId;
	private String userName;
	private String subject;
	private String level;
	private int marks;
	private LocalDateTime date_time;

	public ResultBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultBean(int examId, int studentId, String userName, String subject, String level, int marks,
			LocalDateTime date_time) {
		super();
		this.examId = examId;
		this.studentId = studentId;
		this.userName = userName;
		this.subject = subject;
		this.level = level;
		this.marks = marks;
		this.date_time = date_time;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public LocalDateTime getDate_time() {
		return date_time;
	}

	public void setDate_time(LocalDateTime date_time) {
		this.date_time = date_time;
	}

}
