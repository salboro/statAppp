package ru.tpu.statappp.domain.repository

import java.util.*

interface CurrencyRepository {

    suspend fun getNames(): Map<String, String>

    suspend fun getStatistic(currency: String, start: Date, end: Date): Map<String, Double>
}