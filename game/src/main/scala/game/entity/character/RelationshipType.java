package game.entity.character;

public enum RelationshipType {
	
	MarriedTo("spouse"),
	ParentOf("child"),
	ChildOf("parent"),
	SiblingsWith("sibling"),
	AcquaintencesWith("acquaintence"),
	FriendsWith("friend"),
	InLoveWith("love interest"),
	EnemiesWith("enemy")
	;
	
	private String objectOfRelationship;
	
	private RelationshipType(String objectOfRelationship){
		this.objectOfRelationship = objectOfRelationship;
	}

	public String getObjectOfRelationship() {
		return objectOfRelationship;
	}

	
	public static final int MIN_VAL = -255;
	public static final int MAX_VAL = 255;
}
