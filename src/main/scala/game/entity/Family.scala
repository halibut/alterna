package game.entity

class Family {
    
    var lastName:String = "";

    var head1:Option[Character] = None
    var head2:Option[Character] = None
    
    var children:List[Character] = List();
    
    def heads:List[Character] = {
      List(head1, head2).filter(_.isDefined).map(_.get)
    } 
    
    def addHead(character:Character):Unit = {
      if(!heads.contains(character)){
	      if(head1.isEmpty){
	        head1 = Some(character)
	      }
	      else if (head2.isEmpty){
	        head2 = Some(character)
	      }
	      else{
	        throw new IllegalStateException("Cannot have more than 2 heads of a family.")
	      }
	      character.setHeadOfFamily(this)
      }
    }
    
    def addMember(character:Character):Unit = {
      if(!children.contains(character)){
        children = children ::: List(character)
      }
      character.addMember(this)
    }
    
    override def toString():String = {
      lastName + " Family:\n" +
      	" Heads: " + heads.map(_.basicInfo()).mkString(", ") + "\n" +
      	" Children: " + children.map(_.basicInfo()).mkString(", ")
    }
}