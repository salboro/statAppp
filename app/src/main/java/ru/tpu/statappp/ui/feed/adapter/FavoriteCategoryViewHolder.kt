package ru.tpu.statappp.ui.feed.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.tpu.statappp.R
import ru.tpu.statappp.databinding.FeedFavoriteItemBinding
import ru.tpu.statappp.domain.entity.FavoriteCategory
import ru.tpu.statappp.util.create

class FavoriteCategoryViewHolder(
    parent: ViewGroup,
    onClick: (topicName: String, name: String) -> Unit,
) : RecyclerView.ViewHolder(create(parent, R.layout.feed_favorite_item)) {

    private val binding = FeedFavoriteItemBinding.bind(itemView)
    private lateinit var favoriteCategory: FavoriteCategory

    init {
        binding.root.setOnClickListener {
            onClick(
                favoriteCategory.topicName,
                favoriteCategory.statistic.name
            )
        }
    }

    fun bind(favoriteCategory: FavoriteCategory) {
        this.favoriteCategory = favoriteCategory
        val statistic = favoriteCategory.statistic
        with(binding) {
            category.text = favoriteCategory.topicName
            title.text = statistic.name
            currentValue.text = getStringByValue(statistic.value)
            diffValue.text = statistic.diffValue.toString()
            diffValue.setTextColor(getDiffColor(statistic.value))
        }
    }

    private fun getStringByValue(from: Double): String =
        if (from >= 0.0) {
            itemView.context.getString(R.string.value_more_than_zero, from)
        } else {
            itemView.context.getString(R.string.value_less_than_zero, from)
        }

    private fun getDiffColor(value: Double) = itemView.context.getColor(
        if (value > 0) {
            R.color.green
        } else {
            android.R.color.holo_red_dark
        }
    )
}