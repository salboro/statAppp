package ru.tpu.statappp.domain.entity

data class FavoriteCategory(
    val topicName: String,
    val statistic: ConcreteStatistic,
)

data class ConcreteStatistic(
    val name: String,
    val value: Long,
    val diffValue: Long,
)

data class StatisticTopic(
    val topicName: String,
    val statistics: List<ConcreteStatistic>,
)