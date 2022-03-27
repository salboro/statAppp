package ru.tpu.statappp.domain

import ru.tpu.statappp.domain.repository.StockRepository
import javax.inject.Inject

class GetStockNamesUseCase @Inject constructor(
    private val stockRepository: StockRepository
) {

    suspend operator fun invoke(): List<String> =
        stockRepository.getNames()
}