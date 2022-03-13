package ru.tpu.statappp.presentation.feed

import ru.tpu.statappp.domain.entity.ConcreteStatistic
import ru.tpu.statappp.domain.entity.FavoriteCategory
import ru.tpu.statappp.domain.entity.SelectDetail
import ru.tpu.statappp.domain.entity.StatisticTopic
import ru.tpu.statappp.ui.feed.FeedItem


val concreteStatistic1 = ConcreteStatistic(
    "Доллары",
    123.2,
    12.0
)
val concreteStatistic2 = ConcreteStatistic(
    "ETH",
    1232.78,
    12.12
)
val concreteStatistic3 = ConcreteStatistic(
    "Сбербанк",
    0.1,
    -123.1
)

val favorites = FeedItem.Favorites(listOf(
    FavoriteCategory("Валюта", concreteStatistic1),
    FavoriteCategory("Крипта", concreteStatistic2),
    FavoriteCategory("Акции", concreteStatistic3)
))

val topicItem2 = FeedItem.Topic(
    StatisticTopic(
        "Крипта", listOf(concreteStatistic2, concreteStatistic2, concreteStatistic2)
    )
)

val topicItem1 = FeedItem.Topic(
    StatisticTopic(
        "Валюта", listOf(concreteStatistic1, concreteStatistic1, concreteStatistic1)
    )
)

val topicItem3 = FeedItem.Topic(
    StatisticTopic(
        "Акции", listOf(concreteStatistic3, concreteStatistic3, concreteStatistic3)
    )
)

val feedItems = listOf(
    favorites,
    topicItem1,
    topicItem2,
    topicItem3
)

val selectDetailList = listOf(
    SelectDetail("1", "Евро"),
    SelectDetail("2", "Доллар"),
    SelectDetail("3", "Польский злотый"),
    SelectDetail("4", "Сомы"),
)