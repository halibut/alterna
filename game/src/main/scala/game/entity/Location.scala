package game.entity

import game.entity.data.LocationName
import game.entity.character.Character

class Location() {
    var name:LocationName = null;
    private var characters:List[Character] = List();
    var world:Option[World] = None
    
    
    def addCharacter(character:Character):Unit = {
      characters = characters.filterNot(_ == character) :+ character
    }
    
    def removeCharacter(character:Character):Unit = {
      characters = characters.filterNot(_ == character)
    }
    
    def livingCharacters:Seq[Character] = {
      characters.filterNot(_.isDeceased)
    }
}

