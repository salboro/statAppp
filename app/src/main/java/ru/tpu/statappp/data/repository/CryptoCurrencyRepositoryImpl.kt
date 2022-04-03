package ru.tpu.statappp.data.repository

import ru.tpu.statappp.data.api.StatApppi
import ru.tpu.statappp.domain.repository.CryptoCurrencyRepository
import java.util.*
import javax.inject.Inject

class CryptoCurrencyRepositoryImpl @Inject constructor(
    private val api: StatApppi
): CryptoCurrencyRepository {

    override suspend fun getNames(): List<String> =
        api.getCryptoCurrency()

    override suspend fun getStatistic(
        currency: String,
        start: Date,
        end: Date
    ): Map<String, Double> =
        api.getCryptoDetailed(currency, start, end)
}