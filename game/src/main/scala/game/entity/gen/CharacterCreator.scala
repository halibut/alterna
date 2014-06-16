package game.entity.gen

import game.entity.character.Character
import game.entity.character.CharacterSex
import game.entity.character.CharacterSex._
import game.entity.data.CharacterNames
import game.random.Bag
import game.random.Bag._
import game.random.Random
import game.entity.character.CharacterQuality

object CharacterCreator {
  
  val sexBag = Bag.fromItems(Male,Female)
  
  val qualityOdds = Bag(
      CharacterQuality.S -> 1,
      CharacterQuality.A -> 2,
      CharacterQuality.B -> 5,
      CharacterQuality.C -> 10,
      CharacterQuality.D -> 5,
      CharacterQuality.F -> 2
   )
  
  
  def initCharacter:Character = {
    val character = new Character()
   
    character.name = CharacterNames.nameBag.get.get
    character.sex = sexBag.get.get
    character.gender = Random.bagRand.randDouble(0.65).toFloat
    if(character.sex == Female){
      character.gender += 0.35f
    }
    
    character.quality = qualityOdds.get.get
    
    character.personality.randomize
    
    character
  }

}