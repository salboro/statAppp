package ru.tpu.statappp.domain.repository

import java.util.*

interface CryptoCurrencyRepository {

    suspend fun getNames(): List<String>

    suspend fun getStatistic(currency: String, start: Date, end: Date): Map<String, Double>
}