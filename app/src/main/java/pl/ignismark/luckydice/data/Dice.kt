package pl.ignismark.luckydice.data

class Dice(
    val values: Map<Any, Int>
) {
    val sides: Int = values.size
    fun diceRoll() {

    }
}

