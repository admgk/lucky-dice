package pl.ignismark.luckydice.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.time.LocalDateTime

data class Result(
    val diceName: String,
    @StringRes val value: Int,
    @DrawableRes val graphic: Int,
    val time: LocalDateTime
)