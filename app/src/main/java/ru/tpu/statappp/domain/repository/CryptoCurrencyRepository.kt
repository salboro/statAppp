package ru.tpu.statappp.domain.repository

interface CryptoCurrencyRepository {

    suspend fun getNames(): List<String>
}