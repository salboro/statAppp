package ru.tpu.statappp.domain.repository

import ru.tpu.statappp.domain.entity.Feed

interface FeedRepository {

    suspend fun get(): Feed
}