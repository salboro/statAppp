package ru.tpu.statappp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.tpu.statappp.data.api.StatApppi
import javax.inject.Singleton

const val BASE_URL = "http://10.0.2.2:8000/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    fun provideHttpLogger(): Interceptor =
        HttpLoggingInterceptor().setLevel(Level.BODY)

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): StatApppi =
        retrofit.create(StatApppi::class.java)
}