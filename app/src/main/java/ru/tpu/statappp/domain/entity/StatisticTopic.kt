package ru.tpu.statappp.domain.entity

data class FavoriteCategory(
    val topicName: String,
    val statistic: ConcreteStatistic,
)

data class ConcreteStatistic(
    val name: String,
    val shortName: String?,
    val value: Double,
    val diffValue: Double?,
)

data class StatisticTopic(
    val topicName: String,
    val statistics: List<ConcreteStatistic>,
)