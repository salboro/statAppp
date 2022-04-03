package ru.tpu.statappp.data.repository

import ru.tpu.statappp.data.api.StatApppi
import ru.tpu.statappp.domain.repository.StockRepository
import java.util.*
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val api: StatApppi
): StockRepository {

    override suspend fun getNames(): List<String> =
        api.getStock()

    override suspend fun getStatistic(ticker: String, start: Date, end: Date): Map<String, Double> =
        api.getStockDetailed(ticker, start, end)
}