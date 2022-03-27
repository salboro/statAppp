package ru.tpu.statappp.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.tpu.statappp.data.model.CurrencyDetailedContainerModel
import ru.tpu.statappp.data.model.FeedModel
import java.util.*

interface StatApppi {

    @GET("main_panel")
    suspend fun getMain(): FeedModel

    @GET("currency_panel")
    suspend fun getCurrency(): Map<String, String>

    @GET("currency_detailed_panel")
    suspend fun getCurrencyDetailed(
        @Query("currency") currency: String,
        @Query("start") start: Date,
        @Query("end") end: Date,
    ): CurrencyDetailedContainerModel

    @GET("stock_panel")
    suspend fun getStock(): List<String>

    @GET("stock_detailed_panel")
    suspend fun getStockDetailed(
        @Query("ticker") ticker: String,
        @Query("start") start: Date,
        @Query("end") end: Date,
    ): Map<String, Double>

    @GET("cryptocurrency_panel")
    suspend fun getCryptoCurrency(): List<String>
}