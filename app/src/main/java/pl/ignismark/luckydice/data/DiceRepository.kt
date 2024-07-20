package pl.ignismark.luckydice.data

import pl.ignismark.luckydice.R

object DiceRepository {
    val diceSixSides = Dice(
        values = mapOf(
            Pair(1, R.drawable.dice_1_6),
            Pair(2, R.drawable.dice_2_6),
            Pair(3, R.drawable.dice_3_6),
            Pair(4, R.drawable.dice_4_6),
            Pair(5, R.drawable.dice_5_6),
            Pair(6, R.drawable.dice_6_6)
        )
    )
}