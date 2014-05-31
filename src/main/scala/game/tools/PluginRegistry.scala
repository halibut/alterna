package game.tools

import scala.collection.immutable.HashSet
import scala.reflect.runtime.universe
import scala.reflect.{ClassTag,classTag}
import scala.io.Source
import scala.io.Codec
import java.io.File

abstract class PluginRegistry[T : ClassTag] {
  private val cls = classTag[T].runtimeClass

  private var registry = HashSet[T]()
  
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
    
    for{file <- allFiles
        val modName = file.getName().replaceAll("\\.class", "")
        val clsName = pkg + "." + modName
        if(clsName.indexOf("$") < 0)
    }{
      try{
	      val module = runtimeMirror.staticModule(clsName)
	      val obj = runtimeMirror.reflectModule(module)
	      if(cls.isAssignableFrom(obj.getClass())){
	        registerPlugin(cls.asInstanceOf[T])
	      }
      }catch{
        case e:Exception => println("Error loading plugin "+clsName+". -"+e.getClass()+"-"+e.getMessage())
      }
    }
  }
  
  def registeredPlugins:HashSet[T] = registry
  
  
}