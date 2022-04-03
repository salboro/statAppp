package ru.tpu.statappp.ui.feed.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.tpu.statappp.domain.entity.FavoriteCategory

class FavoritesContainerAdapter(
    private val onClick: (topicName: String, name: String) -> Unit,
) : RecyclerView.Adapter<FavoriteCategoryViewHolder>() {

    var items: List<FavoriteCategory> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCategoryViewHolder =
        FavoriteCategoryViewHolder(parent, onClick)

    override fun onBindViewHolder(holder: FavoriteCategoryViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size
}

