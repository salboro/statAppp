package ru.tpu.statappp.presentation.selectdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.tpu.statappp.domain.entity.SelectDetail
import ru.tpu.statappp.presentation.feed.selectDetailList
import ru.tpu.statappp.ui.details.DetailsFragment
import ru.tpu.statappp.ui.selectdetails.SelectDetailsFragment
import ru.tpu.statappp.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class SelectDetailsViewModel @Inject constructor(
    arguments: SavedStateHandle
) : ViewModel() {

    private val _state = MutableLiveData<SelectDetailsState>(SelectDetailsState.Initial)
    val state: LiveData<SelectDetailsState> = _state

    private val _navigateToDetailsEvent = SingleLiveEvent<String>()
    val navigateToDetailsEvent: LiveData<String> = _navigateToDetailsEvent

    private val topic: String = requireNotNull(arguments[SelectDetailsFragment.TOPIC_KEY])

    fun loadData() {
        _state.value = SelectDetailsState.Content(topic, selectDetailList)
    }

    fun selectDetails(item: SelectDetail) {
        _navigateToDetailsEvent.value = item.id
    }
}