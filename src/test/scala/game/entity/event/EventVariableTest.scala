package game.entity.event

import org.junit.Test
import org.junit.Assert._

class EventVariableTest {
  
  @Test
  def testReplaceVariable(){
    val test = "This is a ${replace} string."
      
    val map = Map("replace" -> "cool")  
      
    val result = EventVariable.replaceVars(test, map)
    
    assertEquals("This is a cool string.", result)
  }
  
  
}