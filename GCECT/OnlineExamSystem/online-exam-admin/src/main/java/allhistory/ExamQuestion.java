package allhistory;

public class ExamQuestion {
	private int examId;
	private int questionId;

	public ExamQuestion() {
		super();
	}

	public ExamQuestion(int examid, int qid) {
		super();
		this.examId = examid;
		this.questionId = qid;
	}

	public int getExamid() {
		return examId;
	}

	public void setExamid(int examid) {
		this.examId = examid;
	}

	public int getQid() {
		return questionId;
	}

	public void setQid(int qid) {
		this.questionId = qid;
	}

}
