package allhistory;

public class ExamQuestion {
	private int examid;
	private int qid;

	public ExamQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExamQuestion(int examid, int qid) {
		super();
		this.examid = examid;
		this.qid = qid;
	}

	public int getExamid() {
		return examid;
	}

	public void setExamid(int examid) {
		this.examid = examid;
	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

}
