package game.tools

import scala.reflect.runtime.universe
import scala.reflect.{ClassTag,classTag}
import scala.io.Source
import scala.io.Codec
import java.io.File

abstract class PluginRegistry[T : ClassTag] {
  private val cls = classTag[T].runtimeClass

  private var registry = Set[T]()
  
  def registerPlugin(plugin:T):Unit = {
    println("Loaded plugin "+plugin.getClass.getName()+" ["+cls.getSimpleName()+"]")
    registry = registry + plugin
  }
  
  def registerSimilarPlugins(plugin:T):Unit = {
    val pluginCls = plugin.getClass
    val pkg = pluginCls.getPackage().getName()
    val pluginUri = pluginCls.getResource(pluginCls.getSimpleName().replaceAll("\\$","")+".class")
    val allFiles = new File(pluginUri.toURI()).getParentFile().listFiles()
    
    val runtimeMirror = universe.runtimeMirror(getClass.getClassLoader)
    
    val filteredFiles = allFiles.map{file =>
      pkg + "." + file.getName().replaceAll("\\.class", "")
    }.filter{name =>
      name.indexOf('$') < 0
    }
    
    for{file <- filteredFiles}{
      try{
	      val module = runtimeMirror.staticModule(file)
	      val obj = runtimeMirror.reflectModule(module).instance
	      if(cls.isAssignableFrom(obj.getClass())){
	        registerPlugin(obj.asInstanceOf[T])
	      }
      }catch{
        case e:Exception => println("Error loading plugin "+file+". -"+e.getClass()+"-"+e.getMessage())
      }
    }
  }
  
  def registeredPlugins:Set[T] = registry
  
  def pluginsBySubType[ST <: T : ClassTag]:Set[ST] = {
    val ct = classTag[ST]
    registry.filter(p => ct.runtimeClass.isAssignableFrom(p.getClass)).asInstanceOf[Set[ST]]
  }
  
}