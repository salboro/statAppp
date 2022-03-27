package ru.tpu.statappp.domain

import ru.tpu.statappp.domain.repository.CurrencyRepository
import javax.inject.Inject

class GetCurrencyNamesUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {

    suspend operator fun invoke(): Map<String, String> =
        repository.getNames()
}