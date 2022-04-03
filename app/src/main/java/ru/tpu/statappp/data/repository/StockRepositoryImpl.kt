package ru.tpu.statappp.data.repository

import ru.tpu.statappp.data.api.StatApppi
import ru.tpu.statappp.data.converter.DateResolutionConverter
import ru.tpu.statappp.domain.entity.DateResolution
import ru.tpu.statappp.domain.repository.StockRepository
import java.util.*
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val api: StatApppi,
    private val dateResolutionConverter: DateResolutionConverter,
): StockRepository {

    override suspend fun getNames(): List<String> =
        api.getStock()

    override suspend fun getStatistic(
        ticker: String,
        resolution: DateResolution
    ): Map<String, Double> {
        val end = Date()
        val start = dateResolutionConverter.convert(end, resolution)

        return api.getStockDetailed(ticker, start, end)
    }
}