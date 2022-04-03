package ru.tpu.statappp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedModel(
    @Json(name = "collection") val collection: Collection,
    @Json(name = "stocks") val stocks: Map<String, Double>,
    @Json(name = "currencies") val currencies: Map<String, CurrencyModel>,
    @Json(name = "cryptocurrencies") val cryptoCurrencies: Map<String, Double>
)

@JsonClass(generateAdapter = true)
data class Collection(
    @Json(name = "stock") val stocks: Map<String, Double>,
    @Json(name = "currency") val currencies: Map<String, CurrencyModel>,
    @Json(name = "cryptocurrency") val cryptoCurrencies: Map<String, Double>
)

