package game.entity.character

trait RelationshipType{
    val name:String
}

object RelationshipTypes{
    
    case object ParentOf extends RelationshipType{
        override val name = "parent"
    }
    case object ChildOf extends RelationshipType{
        override val name = "child"
    }
    case object SiblingsWith extends RelationshipType{
        override val name = "sibling"
    }
    case object CousinsWith extends RelationshipType{
        override val name = "cousin"
    }
    case object GrandParentOf extends RelationshipType{
        override val name = "grandparent"
    }
    
    case object NotRelated extends RelationshipType{
        override val name = "not related to"
    }
    case object AcquaintencesWith extends RelationshipType{
        override val name = "acquaintence"
    }
    case object FriendsWith extends RelationshipType{
        override val name = "friend"
    }
    case object InLoveWith extends RelationshipType{
        override val name = "in love"
    }
    case object EnemiesWith extends RelationshipType{
        override val name = "enemy"
    }
    
}