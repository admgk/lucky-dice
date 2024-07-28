package pl.ignismark.luckydice.data

import pl.ignismark.luckydice.data.Result
import java.time.LocalDateTime

object ResultRepository {
    val startResult = Result(
            DiceRepository.diceSixSides.name,
            DiceRepository.diceSixSides.values[5],
            DiceRepository.diceSixSides.graphics[5],
            LocalDateTime.now()
    )
    val results: MutableList<Result> = mutableListOf()
}