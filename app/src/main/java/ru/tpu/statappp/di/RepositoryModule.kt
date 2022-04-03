package ru.tpu.statappp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.tpu.statappp.data.repository.*
import ru.tpu.statappp.domain.repository.*
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

    @Binds
    fun bindFavoriteRepository(impl: FavoriteRepositoryImpl): FavoriteRepository
}