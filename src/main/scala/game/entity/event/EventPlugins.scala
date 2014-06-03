package game.entity.event

import game.tools.PluginRegistry

object EventPlugins extends PluginRegistry[Event] {

  def characterEvents:Seq[CharacterEvent] = {
    pluginsBySubType[CharacterEvent].toSeq
  }
  
  def relationshipEvents:Seq[RelationshipEvent] = {
    pluginsBySubType[RelationshipEvent].toSeq
    
  }
  
}