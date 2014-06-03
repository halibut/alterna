package game.entity.character;

public enum RelationshipType {
	
	MarriedTo("spouse"),
	ParentOf("child"),
	ChildOf("parent"),
	SiblingsWith("sibling"),
	CousinsWith("cousin"),
	GrandParentOf("grandchild"),
	Acquaintences("acquaintence"),
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

	
}
