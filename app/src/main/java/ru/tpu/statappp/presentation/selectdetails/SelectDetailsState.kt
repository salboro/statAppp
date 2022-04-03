package ru.tpu.statappp.presentation.selectdetails

import ru.tpu.statappp.domain.entity.SelectDetail

sealed interface SelectDetailsState {

    object Initial : SelectDetailsState

    object Loading : SelectDetailsState

    data class Content(val items: List<SelectDetail>) : SelectDetailsState
}