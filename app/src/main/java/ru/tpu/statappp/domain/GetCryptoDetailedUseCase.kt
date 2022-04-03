package ru.tpu.statappp.domain

import ru.tpu.statappp.domain.entity.DateResolution
import ru.tpu.statappp.domain.repository.CryptoCurrencyRepository
import javax.inject.Inject

class GetCryptoDetailedUseCase @Inject constructor(
    private val cryptoCurrencyRepository: CryptoCurrencyRepository
) {

    suspend operator fun invoke(currency: String, resolution: DateResolution) =
        cryptoCurrencyRepository.getStatistic(currency, resolution)
}