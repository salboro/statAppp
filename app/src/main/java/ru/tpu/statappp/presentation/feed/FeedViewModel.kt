package ru.tpu.statappp.presentation.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.tpu.statappp.domain.entity.FavoriteCategory
import ru.tpu.statappp.domain.entity.StatisticTopic
import ru.tpu.statappp.ui.feed.FeedItem
import ru.tpu.statappp.util.SingleLiveEvent

class FeedViewModel : ViewModel() {

    private val _state = MutableLiveData<FeedState>(FeedState.Initial)
    val state: LiveData<FeedState> = _state

    val navigateToMoreEvent = SingleLiveEvent<String>()

    fun loadData() {

    }

    fun selectTopic(topic: StatisticTopic) {
        navigateToMoreEvent.value = topic.topicName
    }

    fun selectFavorite(favorite: FavoriteCategory) {

    }
}