package pl.ignismark.luckydice.data

import pl.ignismark.luckydice.data.Result
import java.time.LocalDateTime

object ResultRepository {
    val mockResults = listOf(
        Result(
            DiceRepository.diceSixSides.name,
            DiceRepository.diceSixSides.values[3],
            DiceRepository.diceSixSides.graphics[3],
            LocalDateTime.now()
        ),
        Result(
            DiceRepository.diceSixSides.name,
            DiceRepository.diceSixSides.values[1],
            DiceRepository.diceSixSides.graphics[1],
            LocalDateTime.of(2024, 7, 20, 18, 57)
        ),
        Result(
            DiceRepository.diceSixSides.name,
            DiceRepository.diceSixSides.values[5],
            DiceRepository.diceSixSides.graphics[5],
            LocalDateTime.of(2024, 6, 17, 21, 17)
        )
    )
    val results: MutableList<Result> = mutableListOf()
}