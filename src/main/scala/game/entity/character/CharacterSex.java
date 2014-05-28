package game.entity.character;

public enum CharacterSex {
	Male("himself","he","him","his"),
	Female("herself","she","her","her")
	;
	
	private CharacterSex(String reflexive, String subject, String object, String posessive){
		this.reflexivePronoun = reflexive;
		this.subjectPronoun = subject;
		this.objectPronoun = object;
		this.posessivePronoun = posessive;
	}
	
	private String reflexivePronoun;
	private String subjectPronoun;
	private String objectPronoun;
	private String posessivePronoun;
	
	public String getReflexivePronoun() {
		return reflexivePronoun;
	}
	public String getSubjectPronoun() {
		return subjectPronoun;
	}
	public String getObjectPronoun() {
		return objectPronoun;
	}
	public String getPosessivePronoun() {
		return posessivePronoun;
	}

}
