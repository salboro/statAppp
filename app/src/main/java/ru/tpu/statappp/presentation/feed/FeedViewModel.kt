package ru.tpu.statappp.presentation.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.tpu.statappp.domain.GetFeedUseCase
import ru.tpu.statappp.domain.entity.*
import ru.tpu.statappp.ui.feed.FeedItem
import ru.tpu.statappp.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getFeedUseCase: GetFeedUseCase,
) : ViewModel() {

    private companion object {
        const val CRYPTO_CURRENCY_TOPIC_NAME = "Криптовалюта"
        const val CURRENCY_TOPIC_NAME = "Валюта"
        const val STOCK_TOPIC_NAME = "Акции"
    }

    private val _state = MutableLiveData<FeedState>(FeedState.Initial)
    val state: LiveData<FeedState> = _state

    private val _navigateToMoreEvent = SingleLiveEvent<String>()
    val navigateToMoreEvent: LiveData<String> = _navigateToMoreEvent

    init {
        loadData()
    }

    private fun loadData() {
        _state.value = FeedState.Loading
        viewModelScope.launch {
            val feed = getFeedUseCase()

            val cryptoCurrencies = feed.cryptoCurrencies.map {
                ConcreteStatistic(it.name, null, it.value, diffValue = null)
            }

            val stocks = feed.stocks.map {
                ConcreteStatistic(it.name, null, it.value, diffValue = null)
            }

            val currencies = feed.currencies.map {
                ConcreteStatistic(it.name, it.shortName, it.value, diffValue = null)
            }

            val favorites: List<FavoriteCategory> = feed.favorites.map {
                getFavoriteCategory(it)
            }

            val feedItems = listOf(
                FeedItem.Favorites(favorites),
                FeedItem.Topic(StatisticTopic(CRYPTO_CURRENCY_TOPIC_NAME, cryptoCurrencies)),
                FeedItem.Topic(StatisticTopic(CURRENCY_TOPIC_NAME, currencies)),
                FeedItem.Topic(StatisticTopic(STOCK_TOPIC_NAME, stocks)),
            )

            _state.value = FeedState.Content(feedItems)
        }
    }

    private fun getFavoriteCategory(topic: Topic): FavoriteCategory =
        when (topic) {
            is Currency -> {
                FavoriteCategory(
                    CURRENCY_TOPIC_NAME,
                    ConcreteStatistic(topic.name, topic.shortName, topic.value, diffValue = null)
                )
            }
            is CryptoCurrency -> {
                FavoriteCategory(
                    CRYPTO_CURRENCY_TOPIC_NAME,
                    ConcreteStatistic(topic.name, null, topic.value, diffValue = null)
                )
            }
            is Stock -> {
                FavoriteCategory(
                    STOCK_TOPIC_NAME,
                    ConcreteStatistic(topic.name, null, topic.value, diffValue = null)
                )
            }
        }

    fun selectTopic(topic: StatisticTopic) {
        _navigateToMoreEvent.value = topic.topicName
    }
}