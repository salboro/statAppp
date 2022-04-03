package ru.tpu.statappp.data.repository

import ru.tpu.statappp.data.api.StatApppi
import ru.tpu.statappp.domain.repository.CurrencyRepository
import java.util.*
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val api: StatApppi
) : CurrencyRepository {

    override suspend fun getNames(): Map<String, String> =
        api.getCurrency()

    override suspend fun getStatistic(
        currency: String,
        start: Date,
        end: Date
    ): Map<String, Double> =
        api.getCurrencyDetailed(currency, start, end)
}