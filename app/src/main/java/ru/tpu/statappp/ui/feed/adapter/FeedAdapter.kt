package ru.tpu.statappp.ui.feed.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.tpu.statappp.domain.entity.FavoriteCategory
import ru.tpu.statappp.domain.entity.StatisticTopic
import ru.tpu.statappp.ui.feed.FeedItem

class FeedAdapter(
    private val onFavoriteClick: (FavoriteCategory) -> Unit,
    private val onTopicMoreClick: (StatisticTopic) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private companion object {

        const val FAVORITE_CONTAINER_VIEW = 0
        const val STATISTIC_TOPIC_VIEW = 1
    }

    var items: List<FeedItem> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int =
        when (items[position]) {
            is FeedItem.Favorites -> FAVORITE_CONTAINER_VIEW
            is FeedItem.Topic -> STATISTIC_TOPIC_VIEW
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            FAVORITE_CONTAINER_VIEW -> FeedFavoritesContainerViewHolder(parent, onFavoriteClick)
            STATISTIC_TOPIC_VIEW -> FeedStatisticViewHolder(parent, onTopicMoreClick)
            else -> throw IllegalArgumentException("Unknown viewType: $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is FeedStatisticViewHolder -> holder.bind(item as FeedItem.Topic)
            is FeedFavoritesContainerViewHolder -> holder.bind(item as FeedItem.Favorites)
        }
    }

    override fun getItemCount(): Int = items.size
}