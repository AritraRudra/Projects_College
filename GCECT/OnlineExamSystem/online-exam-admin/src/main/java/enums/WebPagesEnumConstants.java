package enums;

public enum WebPagesEnumConstants {
	HOME_PAGE("index.jsp"),
	ADD_NEW_QUESTION_PAGE("add_new_question.jsp"),
	ERROR_PAGE("displayerror.jsp");
	private String pageEnum;

	private WebPagesEnumConstants(final String pageEnum) {
		this.pageEnum= pageEnum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.pageEnum;
	}
}
