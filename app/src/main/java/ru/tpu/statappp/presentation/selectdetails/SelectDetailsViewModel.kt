package ru.tpu.statappp.presentation.selectdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.tpu.statappp.domain.entity.SelectDetail
import javax.inject.Inject

@HiltViewModel
class SelectDetailsViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableLiveData<SelectDetailsState>(SelectDetailsState.Initial)
    val state: LiveData<SelectDetailsState> = _state

    fun loadData() {}

    fun selectDetails(item: SelectDetail) {}
}