package ru.tpu.statappp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.tpu.statappp.data.repository.CryptoCurrencyRepositoryImpl
import ru.tpu.statappp.data.repository.CurrencyRepositoryImpl
import ru.tpu.statappp.data.repository.FeedRepositoryImpl
import ru.tpu.statappp.data.repository.StockRepositoryImpl
import ru.tpu.statappp.domain.repository.CryptoCurrencyRepository
import ru.tpu.statappp.domain.repository.CurrencyRepository
import ru.tpu.statappp.domain.repository.FeedRepository
import ru.tpu.statappp.domain.repository.StockRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindFeedRepository(impl: FeedRepositoryImpl): FeedRepository

    @Binds
    @Singleton
    fun bindCurrencyRepository(impl: CurrencyRepositoryImpl): CurrencyRepository

    @Binds
    @Singleton
    fun bindStockRepository(impl: StockRepositoryImpl): StockRepository

    @Binds
    @Singleton
    fun bindCryptoRepository(impl: CryptoCurrencyRepositoryImpl): CryptoCurrencyRepository
}