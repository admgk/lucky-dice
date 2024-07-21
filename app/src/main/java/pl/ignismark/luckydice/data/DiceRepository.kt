package pl.ignismark.luckydice.data

import pl.ignismark.luckydice.R

object DiceRepository {
    val diceSixSides = Dice(
        name = "6K",
        values = listOf(1, 2, 3, 4, 5, 6),
        graphics = listOf(
            R.drawable.dice_1_6,
            R.drawable.dice_2_6,
            R.drawable.dice_3_6,
            R.drawable.dice_4_6,
            R.drawable.dice_5_6,
            R.drawable.dice_6_6
        )
    )
}