package ru.tpu.statappp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.tpu.statappp.data.repository.FeedRepositoryImpl
import ru.tpu.statappp.domain.repository.FeedRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindFeedRepository(impl: FeedRepositoryImpl): FeedRepository
}