package ru.tpu.statappp.presentation.selectdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.tpu.statappp.domain.entity.SelectDetail
import ru.tpu.statappp.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class SelectDetailsViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableLiveData<SelectDetailsState>(SelectDetailsState.Initial)
    val state: LiveData<SelectDetailsState> = _state

    private val _navigateToDetailsEvent = SingleLiveEvent<String>()
    val navigateToDetailsEvent: LiveData<String> = _navigateToDetailsEvent

    fun loadData() {}

    fun selectDetails(item: SelectDetail) {
        _navigateToDetailsEvent.value = item.id
    }
}