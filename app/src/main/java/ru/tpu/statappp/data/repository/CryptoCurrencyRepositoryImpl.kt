package ru.tpu.statappp.data.repository

import ru.tpu.statappp.data.api.StatApppi
import ru.tpu.statappp.domain.repository.CryptoCurrencyRepository
import javax.inject.Inject

class CryptoCurrencyRepositoryImpl @Inject constructor(
    private val api: StatApppi
): CryptoCurrencyRepository {

    override suspend fun getNames(): List<String> =
        api.getCryptoCurrency()
}