package ru.tpu.statappp.domain.repository

import java.util.*

interface CurrencyRepository {

    suspend fun getNames(): Map<String, String>

    suspend fun getStatistic(ticker: String, start: Date, end: Date): Map<Date, String>
}