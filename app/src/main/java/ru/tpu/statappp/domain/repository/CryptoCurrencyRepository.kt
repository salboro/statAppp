package ru.tpu.statappp.domain.repository

import ru.tpu.statappp.domain.entity.DateResolution

interface CryptoCurrencyRepository {

    suspend fun getNames(): List<String>

    suspend fun getStatistic(currency: String, resolution: DateResolution): Map<String, Double>
}