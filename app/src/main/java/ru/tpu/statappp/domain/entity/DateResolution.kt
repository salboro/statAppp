package ru.tpu.statappp.domain.entity

enum class DateResolution(val days: Long) {
    TWO_WEEKS(14),
    ONE_MONTH(31),
    SIX_MONTH(180),
    ONE_YEAR(365),
}