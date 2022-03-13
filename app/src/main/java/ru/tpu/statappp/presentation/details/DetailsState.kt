package ru.tpu.statappp.presentation.details

sealed interface DetailsState {

    object Initial : DetailsState

    object Loading : DetailsState

    data class Content(val title: String) : DetailsState
}