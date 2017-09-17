package enums;

public enum QuestionEnums {
	QUESTION("question"),
	LEVEL("level"),
	OPTION_1("option_1"),
	OPTION_2("option_2"),
	OPTION_3("option_3"),
	OPTION_4("option_4"),
	CORRECT_OPTION("correct_option");
	
	
	private String questionEnum;

	private QuestionEnums(final String questionEnum) {
		this.questionEnum= questionEnum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.questionEnum;
	}

}
