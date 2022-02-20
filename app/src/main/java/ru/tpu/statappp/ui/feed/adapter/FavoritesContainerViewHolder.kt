package ru.tpu.statappp.ui.feed.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.tpu.statappp.R
import ru.tpu.statappp.databinding.FeedFavoritesContainerItemBinding
import ru.tpu.statappp.util.create

class FavoritesContainerViewHolder(
    parent: ViewGroup
) : RecyclerView.ViewHolder(create(parent, R.layout.feed_favorites_container_item)) {

    private var binding: FeedFavoritesContainerItemBinding? = null

    init {
        binding = FeedFavoritesContainerItemBinding.bind(itemView)
    }
}