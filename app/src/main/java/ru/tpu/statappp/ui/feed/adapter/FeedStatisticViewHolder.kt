package ru.tpu.statappp.ui.feed.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.tpu.statappp.R
import ru.tpu.statappp.databinding.FeedStatisticItemBinding
import ru.tpu.statappp.domain.entity.ConcreteStatistic
import ru.tpu.statappp.domain.entity.StatisticTopic
import ru.tpu.statappp.ui.feed.FeedItem
import ru.tpu.statappp.util.create

class FeedStatisticViewHolder(
    parent: ViewGroup,
    private val onClick: (StatisticTopic) -> Unit,
) : RecyclerView.ViewHolder(create(parent, R.layout.feed_statistic_item)) {

    private val binding = FeedStatisticItemBinding.bind(itemView)
    private lateinit var statisticTopic: StatisticTopic

    init {
        binding.moreButton.setOnClickListener { onClick(statisticTopic) }
    }

    fun bind(item: FeedItem.Topic) {
        statisticTopic = item.topic
        val firstItem = statisticTopic.statistics[0]
        val secondItem = statisticTopic.statistics[1]
        val thirdItem = statisticTopic.statistics[2]

        binding.title.text = statisticTopic.topicName
        bindFirstItem(firstItem)
        bindSecondItem(secondItem)
        bindThirdItem(thirdItem)
    }

    private fun bindFirstItem(item: ConcreteStatistic) {
        with(binding) {
            firstTopicTitle.text = item.name
            firstCurrentValue.text = item.value.toString()
            firstDiffValue.text = item.diffValue.toString()
            firstDiffValue.setTextColor(getDiffColor(item.diffValue))
        }
    }

    private fun bindSecondItem(item: ConcreteStatistic) {
        with(binding) {
            secondTopicTitle.text = item.name
            secondCurrentValue.text = item.value.toString()
            secondDiffValue.text = item.diffValue.toString()
            secondDiffValue.setTextColor(getDiffColor(item.diffValue))
        }
    }

    private fun bindThirdItem(item: ConcreteStatistic) {
        with(binding) {
            thirdTopicTitle.text = item.name
            thirdCurrentValue.text = item.value.toString()
            thirdDiffValue.text = item.diffValue.toString()
            thirdDiffValue.setTextColor(getDiffColor(item.diffValue))
        }
    }

    private fun getDiffColor(value: Long) = if (value > 0) {
        android.R.color.holo_green_dark
    } else {
        android.R.color.holo_red_dark
    }
}