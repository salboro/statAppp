package ru.tpu.statappp.ui.feed

import ru.tpu.statappp.domain.entity.FavoriteCategory
import ru.tpu.statappp.domain.entity.StatisticTopic

sealed interface FeedItem {

    data class Favorites(val categories: List<FavoriteCategory>) : FeedItem

    data class Topic(val topic: StatisticTopic) : FeedItem
}