package ru.tpu.statappp.ui.feed.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.tpu.statappp.R
import ru.tpu.statappp.databinding.FeedFavoritesContainerItemBinding
import ru.tpu.statappp.domain.entity.FavoriteCategory
import ru.tpu.statappp.ui.feed.FeedItem
import ru.tpu.statappp.util.create

class FeedFavoritesContainerViewHolder(
    parent: ViewGroup,
    private val onClick: (topicName: String, name: String) -> Unit
) : RecyclerView.ViewHolder(create(parent, R.layout.feed_favorites_container_item)) {

    private val binding = FeedFavoritesContainerItemBinding.bind(itemView)

    private var adapter: FavoritesContainerAdapter? = null

    fun bind(item: FeedItem.Favorites) {
        adapter = FavoritesContainerAdapter(onClick)
        adapter?.items = item.categories
        binding.recycler.adapter = adapter
    }
}