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
}
