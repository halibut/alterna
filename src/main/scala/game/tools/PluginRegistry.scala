package game.tools

import scala.collection.immutable.HashSet

trait PluginRegistry[T] {

  private var registry = HashSet[T]()
  
  def registerPlugin(plugin:T):Unit = {
    registry = registry + plugin
  }
  
  def registeredPlugins:HashSet[T] = registry
}