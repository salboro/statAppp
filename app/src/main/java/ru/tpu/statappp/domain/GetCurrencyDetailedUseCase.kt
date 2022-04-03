package ru.tpu.statappp.domain

import ru.tpu.statappp.domain.entity.DateResolution
import ru.tpu.statappp.domain.repository.CurrencyRepository
import javax.inject.Inject

class GetCurrencyDetailedUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {

    suspend operator fun invoke(currency: String, resolution: DateResolution) =
        currencyRepository.getStatistic(currency, resolution)
}