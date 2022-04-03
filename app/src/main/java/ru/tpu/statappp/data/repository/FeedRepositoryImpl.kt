package ru.tpu.statappp.data.repository

import ru.tpu.statappp.data.api.StatApppi
import ru.tpu.statappp.data.converter.CollectionConverter
import ru.tpu.statappp.data.datasource.FavoritesLocalDataSource
import ru.tpu.statappp.domain.entity.*
import ru.tpu.statappp.domain.repository.FeedRepository
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val api: StatApppi,
    private val favoritesLocalDataSource: FavoritesLocalDataSource,
    private val collectionConverter: CollectionConverter,
) : FeedRepository {

    override suspend fun get(): Feed {
        val feedModel = api.getMain(favoritesLocalDataSource.get())

        val favorites = collectionConverter.convert(feedModel.collection)

        val stocks = feedModel.stocks.map {
            Stock(it.key, it.value)
        }
        val currencies = feedModel.currencies.map {
            Currency(it.value.name, it.key, it.value.value / it.value.nominal)
        }
        val cryptos = feedModel.cryptoCurrencies.map {
            CryptoCurrency(it.key, it.value)
        }

        return Feed(favorites, stocks, currencies, cryptos)
    }
}