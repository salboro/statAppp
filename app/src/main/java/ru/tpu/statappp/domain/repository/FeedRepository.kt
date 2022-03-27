package ru.tpu.statappp.domain.repository

interface FeedRepository {

    suspend fun get(): Feed
}