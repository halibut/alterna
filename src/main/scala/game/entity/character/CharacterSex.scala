package game.entity.character

sealed trait CharacterSex

object CharacterSex{

  object Male extends CharacterSex
  object Female extends CharacterSex

}
