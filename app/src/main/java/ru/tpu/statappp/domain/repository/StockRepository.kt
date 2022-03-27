package ru.tpu.statappp.domain.repository

interface StockRepository {

    suspend fun getNames(): List<String>
}