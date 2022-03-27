package ru.tpu.statappp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyModel(
    @Json(name = "Nominal") val nominal: Int,
    @Json(name = "Name") val name: String,
    @Json(name = "Value") val value: Double,
)
