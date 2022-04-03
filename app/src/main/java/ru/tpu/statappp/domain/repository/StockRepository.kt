package ru.tpu.statappp.domain.repository

import ru.tpu.statappp.domain.entity.DateResolution

interface StockRepository {

    suspend fun getNames(): List<String>

    suspend fun getStatistic(ticker: String, resolution: DateResolution): Map<String, Double>
}