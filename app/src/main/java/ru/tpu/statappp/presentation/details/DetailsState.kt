package ru.tpu.statappp.presentation.details

sealed interface DetailsState {

    object Initial : DetailsState

    object Loading : DetailsState

    object Content : DetailsState
}