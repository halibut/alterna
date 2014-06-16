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
  
  @Test
  def testCapitalizeSentences(){
    val test = "this is a test sentence!  it should capitalize all of this (hopefully?) i guess we'll see. another... sentence."
    val expected = "This is a test sentence!  It should capitalize all of this (hopefully?) I guess we'll see. Another... sentence."
      
    val result = test.capitalizeSentences
    
    assertEquals(expected, result)
  }
}