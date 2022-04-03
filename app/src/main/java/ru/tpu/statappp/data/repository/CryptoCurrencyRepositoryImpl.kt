package ru.tpu.statappp.data.repository

import ru.tpu.statappp.data.api.StatApppi
import ru.tpu.statappp.data.converter.DateResolutionConverter
import ru.tpu.statappp.domain.entity.DateResolution
import ru.tpu.statappp.domain.repository.CryptoCurrencyRepository
import java.util.*
import javax.inject.Inject

class CryptoCurrencyRepositoryImpl @Inject constructor(
    private val api: StatApppi,
    private val dateResolutionConverter: DateResolutionConverter,
): CryptoCurrencyRepository {

    override suspend fun getNames(): List<String> =
        api.getCryptoCurrency()

    override suspend fun getStatistic(
        currency: String,
        resolution: DateResolution
    ): Map<String, Double> {
        val end = Date()
        val start = dateResolutionConverter.convert(end, resolution)

        return api.getCryptoDetailed(currency, start, end)
    }
}