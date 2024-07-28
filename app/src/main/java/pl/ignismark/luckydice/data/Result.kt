package pl.ignismark.luckydice.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import pl.ignismark.luckydice.data.ResultRepository.results
import java.time.LocalDateTime

data class Result(
    val diceName: String,
    @StringRes val value: Int,
    @DrawableRes val graphic: Int,
    val time: LocalDateTime
)

const val HISTORY_LIMIT = 30

fun saveResult(result: Result) {
    if (results.size >= HISTORY_LIMIT) results.removeLast()
    results.add(0, result)
}