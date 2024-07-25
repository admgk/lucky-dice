package pl.ignismark.luckydice.data

import java.time.LocalDateTime

class Dice(
    val name: String,
    val values: List<Int>,
    val graphics: List<Int>
) {
    fun diceRoll(): Result {
        val resultScore = (0..values.lastIndex).random()
        val result = Result(
            diceName = name,
            value = values[resultScore],
            graphic = graphics[resultScore],
            time = LocalDateTime.now()
        )
        saveResult(result)
        return result
    }
}

