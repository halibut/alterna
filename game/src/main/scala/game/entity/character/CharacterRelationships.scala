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
  def apply(tp:RelationshipType):Seq[Relationship] = {
    relationships.toSeq.map(_._2).flatten.filter(_.relationshipType == tp)
  }
  
  def all:Map[Character,Seq[Relationship]] = relationships 
  
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
  
  def remove(char:Character):Unit = {
    relationships = relationships.filterNot(_._1 == char)
  }
  
  def removeAll:Unit = {
    relationships = Map().withDefault(x => Seq[Relationship]())
  }
  
  override def toString():String = {
    relationships.toSeq.map{relEntry =>
      relEntry._2.map{rel =>
        f"""${rel.relationshipType.name()}(${rel.strength} - ${rel.from.personality.alignment(rel.to.personality)}%1.2f)"""
      }.mkString(", ") + f"""${relEntry._1.fullName}""" 
    }.mkString("\n")
  }
}