package enums;

public enum SessionEnums {
	SESSION_SUBJECT("SESS_SUBJ");

	private String sessionEnum;

	private SessionEnums(final String sessionEnum) {
		this.sessionEnum= sessionEnum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.sessionEnum;
	}
}
