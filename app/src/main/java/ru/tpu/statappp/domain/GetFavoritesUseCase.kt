package ru.tpu.statappp.domain

import ru.tpu.statappp.domain.entity.Favorite
import ru.tpu.statappp.domain.repository.FavoriteRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {

    operator fun invoke(): List<Favorite> =
        repository.get()
}