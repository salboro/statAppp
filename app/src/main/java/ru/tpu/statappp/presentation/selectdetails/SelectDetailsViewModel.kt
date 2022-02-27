package ru.tpu.statappp.presentation.selectdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.tpu.statappp.domain.entity.SelectDetail

class SelectDetailsViewModel : ViewModel() {

    private val _state = MutableLiveData<SelectDetailsState>(SelectDetailsState.Initial)
    val state: LiveData<SelectDetailsState> = _state

    fun loadData() {}

    fun selectDetails(item: SelectDetail) {}
}