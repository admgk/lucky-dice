package pl.ignismark.luckydice.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

class Dice(
    val name: String,
    val values: List<Int>,
    val graphics: List<Int>
) {
    val sides: Int = values.size
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

