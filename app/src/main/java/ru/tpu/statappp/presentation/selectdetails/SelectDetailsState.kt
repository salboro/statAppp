package ru.tpu.statappp.presentation.selectdetails

import ru.tpu.statappp.domain.entity.SelectDetail
import ru.tpu.statappp.ui.feed.FeedItem

sealed interface SelectDetailsState {

    object Initial : SelectDetailsState

    object Loading : SelectDetailsState

    data class Content(val topic: String, val items: List<SelectDetail>) : SelectDetailsState
}