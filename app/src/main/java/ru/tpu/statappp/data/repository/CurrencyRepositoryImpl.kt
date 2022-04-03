package ru.tpu.statappp.data.repository

import ru.tpu.statappp.data.api.StatApppi
import ru.tpu.statappp.data.converter.DateResolutionConverter
import ru.tpu.statappp.domain.entity.DateResolution
import ru.tpu.statappp.domain.repository.CurrencyRepository
import java.util.*
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val api: StatApppi,
    private val dateResolutionConverter: DateResolutionConverter,
) : CurrencyRepository {

    override suspend fun getNames(): Map<String, String> =
        api.getCurrency()

    override suspend fun getStatistic(
        currency: String,
        resolution: DateResolution
    ): Map<String, Double> {
        val end = Date()
        val start = dateResolutionConverter.convert(end, resolution)

        return api.getCurrencyDetailed(currency, start, end)
    }
}