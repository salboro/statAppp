package ru.tpu.statappp.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.tpu.statappp.data.datasource.FavoritesLocalDataSource
import ru.tpu.statappp.data.datasource.FavoritesLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    companion object {

        @Provides
        @Singleton
        fun providePreferences(@ApplicationContext context: Context): SharedPreferences =
            context.getSharedPreferences("FAVORITE_PREFS", Context.MODE_PRIVATE)
    }

    @Binds
    @Singleton
    fun bindFavoritesLocalDataSource(impl: FavoritesLocalDataSourceImpl): FavoritesLocalDataSource
}