package game.entity

import game.entity.character.Character
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter


class World {

    var locations:Seq[Location] = List();
    var characters:Seq[Character] = List();
    
    var year = {
      DateTime.now().year().setCopy(0)
      	.dayOfYear().setCopy(1)
      	.hourOfDay().setCopy(0)
      	.minuteOfHour().setCopy(0)
      	.secondOfMinute().setCopy(0)
      	.millisOfSecond().setCopy(0)
    }
    
    
    
    def addCharacter(character:Character):Unit = {
      characters = characters.filterNot(_ == character) :+ character
    }
}