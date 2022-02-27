package ru.tpu.statappp.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.tpu.statappp.R
import ru.tpu.statappp.databinding.FragmentFeedBinding
import ru.tpu.statappp.domain.entity.ConcreteStatistic
import ru.tpu.statappp.domain.entity.FavoriteCategory
import ru.tpu.statappp.domain.entity.StatisticTopic
import ru.tpu.statappp.presentation.feed.FeedViewModel
import ru.tpu.statappp.ui.feed.adapter.FeedAdapter
import ru.tpu.statappp.ui.selectdetails.SelectDetailsFragment

class FeedFragment : Fragment() {

    private var binding: FragmentFeedBinding? = null

    private var adapter: FeedAdapter? = null

    private val viewModel: FeedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FeedAdapter(viewModel::selectFavorite, viewModel::selectTopic)
        binding?.recycler?.adapter = adapter

        viewModel.navigateToMoreEvent.observe(viewLifecycleOwner, ::openSelectDetails)
    }

    private fun openSelectDetails(topic: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, SelectDetailsFragment.newInstance(topic))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        binding?.recycler?.adapter = null
        binding = null
        adapter = null
        super.onDestroyView()
    }
}