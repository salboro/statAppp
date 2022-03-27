package ru.tpu.statappp.data.repository

import ru.tpu.statappp.data.api.StatApppi
import ru.tpu.statappp.domain.repository.CurrencyRepository
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val api: StatApppi
) : CurrencyRepository {

    override suspend fun getNames(): Map<String, String> =
        api.getCurrency()

    override suspend fun getStatistic(
        ticker: String,
        start: Date,
        end: Date
    ): Map<Date, String> {
        throw Exception()
    }
}