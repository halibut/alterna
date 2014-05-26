package game.entity

import game.entity.data.LocationName
import game.entity.character.Character

class Location() {
    var name:LocationName = null;
    private var characters:List[Character] = List();
    
    
    def addCharacter(character:Character):Unit = {
      characters = characters.filterNot(_ == character)
      characters = character :: characters 
    }
    
    def removeCharacter(character:Character):Unit = {
      characters = characters.filterNot(_ == character)
    }
}

