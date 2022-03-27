package ru.tpu.statappp.data.repository

import ru.tpu.statappp.data.api.StatApppi
import ru.tpu.statappp.domain.repository.*
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val api: StatApppi
) : FeedRepository {

    override suspend fun get(): Feed {
        val feedModel = api.getMain()

        val stocks = feedModel.stocks.map {
            Stock(it.key, it.value)
        }
        val currencies = feedModel.currencies.map {
            Currency(it.value.name, it.value.value / it.value.nominal)
        }
        val cryptos = feedModel.cryptoCurrencies.map {
            CryptoCurrency(it.key, it.value)
        }

        return Feed(stocks, currencies, cryptos)
    }
}