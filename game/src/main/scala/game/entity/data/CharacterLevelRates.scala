package game.entity.data

import game.random.Bag

object CharacterLevelRates{
  val rates = Map[Int,Int](
      0 -> 1,
      1 -> 1,
      2 -> 1,
      3 -> 1,
      4 -> 1,
      5 -> 1,
      6 -> 1,
      7 -> 1,
      8 -> 1,
      9 -> 1,
      10 -> 1,
      11 -> 1,
      12 -> 2,
      13 -> 2,
      14 -> 2,
      15 -> 2,
      16 -> 2,
      16 -> 2,
      17 -> 2,
      18 -> 1,
      19 -> 1,
      10 -> 1,
      21 -> 1,
      22 -> 1,
      23 -> 1,
      25 -> 1,
      27 -> 1,
      29 -> 1,
      30 -> 1,
      33 -> 1,
      36 -> 1,
      40 -> 1,
      44 -> 1,
      48 -> 1,
      53 -> 1,
      58 -> 1,
      65 -> 1,
      70 -> 1,
      75 -> 1,
      80 -> 1,
      90 -> 1,
      100 -> 1
      ).withDefaultValue(0)
} 