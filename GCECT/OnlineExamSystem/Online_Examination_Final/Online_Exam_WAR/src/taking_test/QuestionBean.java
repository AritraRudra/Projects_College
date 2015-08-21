package taking_test;

public class QuestionBean {
	private int qid;
	private String question;
	private String subject;
	private String level;
	private String opt1;
	private String opt2;
	private String opt3;
	private String opt4;
	private String corr_opt;
	
	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
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

	public String getOpt1() {
		return opt1;
	}

	public void setOpt1(String opt1) {
		this.opt1 = opt1;
	}

	public String getOpt2() {
		return opt2;
	}

	public void setOpt2(String opt2) {
		this.opt2 = opt2;
	}

	public String getOpt3() {
		return opt3;
	}

	public void setOpt3(String opt3) {
		this.opt3 = opt3;
	}

	public String getOpt4() {
		return opt4;
	}

	public void setOpt4(String opt4) {
		this.opt4 = opt4;
	}

	public String getCorr_opt() {
		return corr_opt;
	}

	public void setCorr_opt(String corr_opt) {
		this.corr_opt = corr_opt;
	}

	public QuestionBean() {
		super();
	}
	
	public QuestionBean(int qid, String question, String subject, String level, String opt1,
			String opt2, String opt3, String opt4, String corr_opt) {
		super();
		this.qid = qid;
		this.question = question;
		this.subject = subject;
		this.level = level;
		this.opt1 = opt1;
		this.opt2 = opt2;
		this.opt3 = opt3;
		this.opt4 = opt4;
		this.corr_opt = corr_opt;
	}
	
	public QuestionBean(String question, String subject, String level, String opt1,
			String opt2, String opt3, String opt4, String corr_opt) {
		super();		
		this.question = question;
		this.subject = subject;
		this.level = level;
		this.opt1 = opt1;
		this.opt2 = opt2;
		this.opt3 = opt3;
		this.opt4 = opt4;
		this.corr_opt = corr_opt;
	}	

}
