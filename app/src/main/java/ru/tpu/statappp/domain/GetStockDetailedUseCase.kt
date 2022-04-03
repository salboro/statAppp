package ru.tpu.statappp.domain

import ru.tpu.statappp.domain.entity.DateResolution
import ru.tpu.statappp.domain.repository.StockRepository
import javax.inject.Inject

class GetStockDetailedUseCase @Inject constructor(
    private val stockRepository: StockRepository,
) {

    suspend operator fun invoke(ticker: String, resolution: DateResolution) =
        stockRepository.getStatistic(ticker, resolution)
}