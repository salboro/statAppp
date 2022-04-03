package ru.tpu.statappp.domain

import ru.tpu.statappp.domain.entity.Favorite
import ru.tpu.statappp.domain.repository.FavoriteRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {

    operator fun invoke(favorite: Favorite) {
        repository.delete(favorite)
    }
}