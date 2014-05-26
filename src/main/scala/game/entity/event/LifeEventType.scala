package game.entity.event

trait LifeEventType{
  def name:String
  
  LifeEventType.values = LifeEventType.values :+ this
}

object LifeEventType {

  var values = Seq[LifeEventType]()

  case object Birth extends LifeEventType{val name = "Birth"};
  case object Death extends LifeEventType{val name = "Death"};
  case object NewRelationship extends LifeEventType{val name = "NewRelationship"};
  case object RelationshipChange extends LifeEventType{val name = "RelationshipChange"};
  case object EndRelationship extends LifeEventType{val name = "EndRelationship"};
  case object PhysicalTrial extends LifeEventType{val name = "PhysicalTrial"};
  case object MentalTrial extends LifeEventType{val name = "MentalTrial"};
  case object BeginJob extends LifeEventType{val name = "BeginJob"};
  case object Move extends LifeEventType{val name = "Move"};
}

object RunThis extends App {
  println ("tst")
  println (LifeEventType.values.mkString(","))
  
}