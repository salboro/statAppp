package ru.tpu.statappp.domain

import ru.tpu.statappp.domain.repository.CryptoCurrencyRepository
import java.util.*
import javax.inject.Inject

class GetCryptoDetailedUseCase @Inject constructor(
    private val cryptoCurrencyRepository: CryptoCurrencyRepository
) {

    suspend operator fun invoke(currency: String, startDate: Date, endDate: Date) =
        cryptoCurrencyRepository.getStatistic(currency, startDate, endDate)
}