package ru.tpu.statappp.data.converter

import ru.tpu.statappp.domain.entity.DateResolution
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DateResolutionConverter @Inject constructor() {

    fun convert(from: Date, resolution: DateResolution): Date {
        val resolutionTime = TimeUnit.DAYS.toMillis(resolution.days)

        return Date(from.time - resolutionTime)
    }
}