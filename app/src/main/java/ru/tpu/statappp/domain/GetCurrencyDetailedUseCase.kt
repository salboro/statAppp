package ru.tpu.statappp.domain

import ru.tpu.statappp.domain.repository.CurrencyRepository
import java.util.*
import javax.inject.Inject

class GetCurrencyDetailedUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {

    suspend operator fun invoke(currency: String, startDate: Date, endDate: Date) =
        currencyRepository.getStatistic(currency, startDate, endDate)
}