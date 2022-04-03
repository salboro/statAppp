package ru.tpu.statappp.domain.repository

import ru.tpu.statappp.domain.entity.Favorite

interface FavoriteRepository {

    fun get(): List<Favorite>

    fun add(favorite: Favorite)

    fun delete(favorite: Favorite)
}