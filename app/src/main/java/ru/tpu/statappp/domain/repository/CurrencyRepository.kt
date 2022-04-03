package ru.tpu.statappp.domain.repository

import ru.tpu.statappp.domain.entity.DateResolution

interface CurrencyRepository {

    suspend fun getNames(): Map<String, String>

    suspend fun getStatistic(currency: String, resolution: DateResolution): Map<String, Double>
}