package ru.tpu.statappp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FavoriteModel(
    @Json(name = "type") val type: String,
    @Json(name = "name") val shortName: String,
)
