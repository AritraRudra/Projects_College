package history;

import java.sql.Date;

public class ResultHistoryBean {
	private int examid;
	private int sid;
	private String subject;
	private String level;
	int marks;
	Date exam_date;
	
	public ResultHistoryBean() {
		super();
	}

	public ResultHistoryBean(int examid,int sid,String subject, String level, int marks,
			Date exam_date) {
		super();
		this.examid=examid;
		this.sid=sid;
		this.subject = subject;
		this.level = level;
		this.marks = marks;
		this.exam_date = exam_date;
	}

	
	public int getExamid() {
		return examid;
	}

	public void setExamid(int examid) {
		this.examid=examid;
	}
	
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid=sid;
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

	public Date getExam_date() {
		return exam_date;
	}

	public void setExam_date(Date exam_date) {
		this.exam_date = exam_date;
	}

}
