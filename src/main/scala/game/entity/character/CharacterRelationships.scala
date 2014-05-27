package game.entity.character

class CharacterRelationships {

  private var relationships:Map[Character,Seq[Relationship]] = 
    Map().withDefault(x => Seq[Relationship]())
  
  def add(rel:Relationship):Unit = {
    val curRelationships = relationships(rel.to) 
    		.filterNot(_.relationshipType == rel.relationshipType )
    		
    relationships = relationships.updated(rel.to, curRelationships :+ rel)
  }
  
  def apply(char:Character):Seq[Relationship] = {
    relationships(char)
  }
  
  def apply(char:Character, tp:RelationshipType):Option[Relationship] = {
    relationships(char).find(_.relationshipType == tp)
  }
  
  def remove(rel:Relationship):Unit = {
    val curRelationships = relationships(rel.to) 
    		.filterNot(_.relationshipType == rel.relationshipType )
    
    if(curRelationships.isEmpty){
      relationships = relationships.filterNot(_._2.to == rel.to)
    }
    else{
      relationships = relationships.updated(rel.to, curRelationships)
    }
  }
  
  override def toString():String = {
    relationships.toSeq.map{relEntry =>
      relEntry._2.map{rel =>
        rel.relationshipType.name()+"("+rel.strength+")"
      }.mkString(", ") + " " + relEntry._1.fullName 
    }.mkString("\n")
  }
}