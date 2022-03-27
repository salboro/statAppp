package ru.tpu.statappp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedModel(
    @Json(name = "stocks") val stocks: Map<String, Double>,
    @Json(name = "currencies") val currencies: Map<String, CurrencyModel>,
    @Json(name = "cryptocurrencies") val cryptoCurrencies: Map<String, Double>
)
