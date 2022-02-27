package ru.tpu.statappp.presentation.feed

import ru.tpu.statappp.ui.feed.FeedItem

sealed interface FeedState {

    object Initial : FeedState

    object Loading : FeedState

    data class Content(val items: List<FeedItem>) : FeedState
}