package ru.tpu.statappp.ui.feed.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.tpu.statappp.R
import ru.tpu.statappp.databinding.FeedFavoriteItemBinding
import ru.tpu.statappp.domain.entity.FavoriteCategory
import ru.tpu.statappp.util.create

class FavoriteCategoryViewHolder(
    parent: ViewGroup,
    onClick: (FavoriteCategory) -> Unit,
) : RecyclerView.ViewHolder(create(parent, R.layout.feed_favorite_item)) {

    private val binding = FeedFavoriteItemBinding.bind(itemView)
    private lateinit var favoriteCategory: FavoriteCategory

    init {
        binding.root.setOnClickListener { onClick(favoriteCategory) }
    }

    fun bind(favoriteCategory: FavoriteCategory) {
        this.favoriteCategory = favoriteCategory
        val statistic = favoriteCategory.statistic
        with(binding) {
            category.text = favoriteCategory.topicName
            title.text = statistic.name
            currentValue.text = statistic.value.toString()
            diffValue.text = statistic.diffValue.toString()
        }
    }
}