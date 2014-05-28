package game.entity.event

import org.junit.Test
import org.junit.Assert._
import game.entity.character.Character
import game.entity.character.CharacterSex

class EventVariableTest {
  
  val char = new Character()
  char.sex = CharacterSex.Male
  
  @Test
  def testGetStaticVariable(){
    val test = "variableName = This is a variable value."
      
    val vars = EventVariables(test)
    val map = EventVariables.getEventVars(vars, char)
    
    assertEquals("This is a variable value.",map("variableName"))
  }
  
  @Test
  def testGetChoiceVariable(){
    val test = "varName = Choose(1, 2,3, 4 , 5)"
      
    val vars = EventVariables(test)
    val map = EventVariables.getEventVars(vars, char)
    
    val varVal = map("varName")
    
    assertTrue(Seq("1","2","3","4","5").contains(varVal))
  }
  
  @Test
  def testGetMultipleVariables(){
    val test = "\t\tchoiceVar1 = Choose(1, 2,3, 4 , 5)\n\n" +
    		"\t\tstaticVar1 = hello\n" +
    		"\tchoiceVar2 = Choose(a, ${staticVar1},c)\n" +
    		"staticVar2 = ${staticVar1} world"
      
    val vars = EventVariables(test)
    val map = EventVariables.getEventVars(vars, char)
    
    assertTrue(Seq("1","2","3","4","5").contains(map("choiceVar1")))
    assertTrue(Seq("a","hello","c").contains(map("choiceVar2")))
    assertEquals("hello",map("staticVar1"))
    assertEquals("hello world",map("staticVar2"))
  }
  
  
  
  
}