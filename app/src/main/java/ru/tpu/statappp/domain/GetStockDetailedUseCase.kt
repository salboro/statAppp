package ru.tpu.statappp.domain

import ru.tpu.statappp.domain.repository.StockRepository
import java.util.*
import javax.inject.Inject

class GetStockDetailedUseCase @Inject constructor(
    private val stockRepository: StockRepository,
) {

    suspend operator fun invoke(ticker: String, startDate: Date, endDate: Date) =
        stockRepository.getStatistic(ticker, startDate, endDate)
}