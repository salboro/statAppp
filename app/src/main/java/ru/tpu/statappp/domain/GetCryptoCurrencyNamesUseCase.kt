package ru.tpu.statappp.domain

import ru.tpu.statappp.domain.repository.CryptoCurrencyRepository
import javax.inject.Inject

class GetCryptoCurrencyNamesUseCase @Inject constructor(
    private val repository: CryptoCurrencyRepository
) {

    suspend operator fun invoke(): List<String> =
        repository.getNames()
}