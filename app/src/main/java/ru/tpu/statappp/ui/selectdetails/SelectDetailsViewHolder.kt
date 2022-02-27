package ru.tpu.statappp.ui.selectdetails

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.tpu.statappp.R
import ru.tpu.statappp.databinding.SelectDetailsItemBinding
import ru.tpu.statappp.domain.entity.SelectDetail
import ru.tpu.statappp.util.create

class SelectDetailsViewHolder(
    parent: ViewGroup,
    onClick: (SelectDetail) -> Unit
) : RecyclerView.ViewHolder(create(parent, R.layout.select_details_item)) {

    private val binding = SelectDetailsItemBinding.bind(itemView)
    private var selectDetail: SelectDetail? = null

    init {
        binding.root.setOnClickListener {
            selectDetail?.let { onClick(it) }
        }
    }

    fun bind(item: SelectDetail) {
        selectDetail = item
        binding.title.text = item.name
    }
}