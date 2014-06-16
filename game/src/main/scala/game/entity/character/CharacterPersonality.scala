package game.entity.character

import game.random.Random

class CharacterPersonality {

  private var dimensions = Personality.values.map(dim => (dim, 0)).toMap

  def apply(dim: Personality): Int = {
    dimensions(dim)
  }

  def update(dim: Personality, value: Int) = {
    dimensions = dimensions.updated(dim, math.min(dim.getMax(), math.max(dim.getMin(), value)))
  }

  def randomize: Unit = {
    dimensions = Personality.values.map(dim =>
      (dim, Random.rand.randInt(dim.getMin(), dim.getMax()))
    ).toMap
  }

  override def toString(): String = {
    dimensions.toSeq
      .sortWith { case (d1, d2) => d1._2 > d2._2 }
      .map {
        case (dim, value) =>
          dim.name() + ": " + value
      }
      .mkString("\n")
  }
  
  /**
   * Calculates the alignment with a different character personality
   */
  def alignment(other:CharacterPersonality):Double = {
    val zippedSeq = Personality.values.map{pType =>
      (this(pType).toDouble,other(pType).toDouble)
    }
    
    //calculate the magnitude of each personality vector
    val sumSquares = zippedSeq.map{case (p1,p2) =>
    	(p1 * p1, p2 * p2)
    }.foldLeft((0.0,0.0)){case ((p1s,p2s),(p1,p2)) =>
      (p1s + p1, p2s + p2)
    }
    val mags = (math.sqrt(sumSquares._1), math.sqrt(sumSquares._2))
    
    //Normalize each personality vector
    val normalized = zippedSeq.map{case (c1,c2) =>
      (c1 / mags._1, c2 / mags._2)
    }
    
    //calculate the dot product between the 2 normalized personality vectors
    val persAlign = normalized.map{case (p1,p2) =>
      p1 * p2
    }.sum
    
    persAlign 
  }
}
