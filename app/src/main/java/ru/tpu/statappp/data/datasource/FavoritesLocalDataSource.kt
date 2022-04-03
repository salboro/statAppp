package ru.tpu.statappp.data.datasource

import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import ru.tpu.statappp.data.model.FavoriteModel
import javax.inject.Inject

interface FavoritesLocalDataSource {

    fun get(): List<FavoriteModel>

    fun set(favorites: List<FavoriteModel>)
}

class FavoritesLocalDataSourceImpl @Inject constructor(
    moshi: Moshi,
    private val sharedPreferences: SharedPreferences,
) : FavoritesLocalDataSource {

    private companion object {

        const val FAVORITE_KEY = "FAVORITE_KEY"
    }

    @OptIn(ExperimentalStdlibApi::class)
    private val listAdapter = moshi.adapter<List<FavoriteModel>>()

    override fun get(): List<FavoriteModel> =
        sharedPreferences.getString(FAVORITE_KEY, null)
            ?.let { favoritesRaw -> listAdapter.fromJson(favoritesRaw) }
            ?: emptyList()

    override fun set(favorites: List<FavoriteModel>) {
        val json = listAdapter.toJson(favorites)

        sharedPreferences.edit()
            .putString(FAVORITE_KEY, json)
            .apply()
    }
}