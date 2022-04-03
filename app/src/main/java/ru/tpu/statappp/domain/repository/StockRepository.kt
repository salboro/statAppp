package ru.tpu.statappp.domain.repository

import java.util.*

interface StockRepository {

    suspend fun getNames(): List<String>

    suspend fun getStatistic(ticker: String, start: Date, end: Date): Map<String, Double>
}