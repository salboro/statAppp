package ru.tpu.statappp.ui.selectdetails

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.tpu.statappp.domain.entity.SelectDetail

class SelectDetailsAdapter(
    private val onClick: (SelectDetail) -> Unit
) : RecyclerView.Adapter<SelectDetailsViewHolder>() {

    var items: List<SelectDetail> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectDetailsViewHolder =
        SelectDetailsViewHolder(parent, onClick)

    override fun onBindViewHolder(holder: SelectDetailsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}