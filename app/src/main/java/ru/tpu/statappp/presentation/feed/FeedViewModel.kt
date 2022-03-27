package ru.tpu.statappp.presentation.feed

import android.text.NoCopySpan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.tpu.statappp.domain.GetFeedUseCase
import ru.tpu.statappp.domain.entity.ConcreteStatistic
import ru.tpu.statappp.domain.entity.FavoriteCategory
import ru.tpu.statappp.domain.entity.StatisticTopic
import ru.tpu.statappp.ui.feed.FeedItem
import ru.tpu.statappp.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getFeedUseCase: GetFeedUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<FeedState>(FeedState.Initial)
    val state: LiveData<FeedState> = _state

    private val _navigateToMoreEvent = SingleLiveEvent<String>()
    val navigateToMoreEvent: LiveData<String> = _navigateToMoreEvent

    fun loadData() {
        _state.value = FeedState.Loading
        viewModelScope.launch {
            val feed = getFeedUseCase()

            val cryptoCurrencies = feed.cryptoCurrencies.map {
                ConcreteStatistic(it.name, it.value, diffValue = null)
            }

            val stocks = feed.stocks.map {
                ConcreteStatistic(it.name, it.value, diffValue = null)
            }

            val currencies = feed.currencies.map {
                ConcreteStatistic(it.name, it.value, diffValue = null)
            }

            val feedItems = listOf(
                FeedItem.Topic(StatisticTopic("Криптовалюта", cryptoCurrencies)),
                FeedItem.Topic(StatisticTopic("Валюта", currencies)),
                FeedItem.Topic(StatisticTopic("Акции", stocks)),
            )

            _state.value = FeedState.Content(feedItems)
        }
    }

    fun selectTopic(topic: StatisticTopic) {
        _navigateToMoreEvent.value = topic.topicName
    }

    fun selectFavorite(favorite: FavoriteCategory) {

    }
}