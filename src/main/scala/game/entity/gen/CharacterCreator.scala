package game.entity.gen

import game.entity.character.Character
import game.entity.character.CharacterSex
import game.entity.character.CharacterSex._
import game.entity.data.CharacterNames
import game.random.Bag
import game.random.Random

object CharacterCreator {
  
  val sexBag = Bag[CharacterSex](Male,Female)
  
  
  def initCharacter:Character = {
    val character = new Character()
   
    character.name = CharacterNames.nameBag.get
    character.sex = sexBag.get
    character.gender = Random.bagRand.randDouble(0.65).toFloat
    if(character.sex == Female){
      character.gender += 0.35f
    }
    
    character.personality.randomize
    
    character
  }

}