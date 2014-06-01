package game.entity.event.outcome

import game.entity.character.RelationshipType
import game.entity.character.Character

trait Affects {
  def affected(character:Character):Seq[Character]
}

class CharacterRelations(val relations:RelationshipType*) extends Affects {
  override def affected(character:Character):Seq[Character] = {
    val oChars = for(relationType <- relations; 
        rels <- character.relationships(relationType)) yield {
      rels.to 
    }
    
    oChars
  }
}

class SelfRelation extends Affects {
  override def affected(character:Character):Seq[Character] = {
    Seq(character)
  }
}