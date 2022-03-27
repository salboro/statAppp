package ru.tpu.statappp.domain

import ru.tpu.statappp.domain.entity.Feed
import ru.tpu.statappp.domain.repository.FeedRepository
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(
    private val repository: FeedRepository
) {

    suspend operator fun invoke(): Feed =
        repository.get()
}