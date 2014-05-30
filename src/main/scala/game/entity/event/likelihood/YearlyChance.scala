package game.entity.event.likelihood

case class YearlyChance(chances: Int, outOf: Int) extends Likelihood {
  def chance(character: Character): Double = {
    chances.toDouble / outOf.toDouble
  }
}

