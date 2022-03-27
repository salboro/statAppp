package ru.tpu.statappp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class CurrencyDetailedContainerModel(
    @Json(name = "all_dates") val data: List<CurrencyDetailedModel>
)

@JsonClass(generateAdapter = true)
data class CurrencyDetailedModel(
    @Json(name = "date") val date: Date,
    @Json(name = "value") val value: Double,
)