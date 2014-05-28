package game.tools

import game.tools.StringUtils._
import org.junit.Test
import org.junit.Assert._

class StringUtilsTest {

  @Test
  def testReplaceVariable(){
    val test = "This is a ${replace} string."
      
    val map = Map("replace" -> "cool")  
      
    val result = test.replaceVars(map)
    
    assertEquals("This is a cool string.", result)
  }
}