package pl.ignismark.luckydice.data

import java.time.LocalDateTime

class Dice(
    val name: String,
    val values: List<Int>,
    val graphics: List<Int>
) {
    fun diceRoll(): Result {
        val result = (0..values.lastIndex).random()

        return Result(
            diceName = name,
            value = values[result],
            graphic = graphics[result],
            time = LocalDateTime.now()
        )
    }
}

