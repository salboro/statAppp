package ru.tpu.statappp.presentation.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.tpu.statappp.domain.entity.FavoriteCategory
import ru.tpu.statappp.domain.entity.StatisticTopic
import ru.tpu.statappp.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableLiveData<FeedState>(FeedState.Initial)
    val state: LiveData<FeedState> = _state

    private val _navigateToMoreEvent = SingleLiveEvent<String>()
    val navigateToMoreEvent: LiveData<String> = _navigateToMoreEvent

    fun loadData() {

    }

    fun selectTopic(topic: StatisticTopic) {
        _navigateToMoreEvent.value = topic.topicName
    }

    fun selectFavorite(favorite: FavoriteCategory) {

    }
}