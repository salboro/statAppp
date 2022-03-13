package ru.tpu.statappp.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.tpu.statappp.ui.details.DetailsFragment
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    arguments: SavedStateHandle
) : ViewModel() {

    private val _state = MutableLiveData<DetailsState>(DetailsState.Initial)
    val state: LiveData<DetailsState> = _state

    private val id: String = requireNotNull(arguments[DetailsFragment.ID_KEY])

    private fun loadData() {
        _state.value = DetailsState.Loading
        _state.value = DetailsState.Content("Евро")
    }
}