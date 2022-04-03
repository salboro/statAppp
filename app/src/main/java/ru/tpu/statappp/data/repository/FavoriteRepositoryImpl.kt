package ru.tpu.statappp.data.repository

import ru.tpu.statappp.data.datasource.FavoritesLocalDataSource
import ru.tpu.statappp.data.model.FavoriteModel
import ru.tpu.statappp.domain.entity.Favorite
import ru.tpu.statappp.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dataSource: FavoritesLocalDataSource
): FavoriteRepository {

    override fun get(): List<Favorite> =
        dataSource.get().map {
            Favorite(it.type, it.shortName)
        }

    override fun add(favorite: Favorite) {
        val newFavorites = dataSource.get() + FavoriteModel(favorite.type, favorite.name)
        dataSource.set(newFavorites)
    }

    override fun delete(favorite: Favorite) {
        val newFavorites = dataSource.get() - FavoriteModel(favorite.type, favorite.name)
        dataSource.set(newFavorites)
    }
}