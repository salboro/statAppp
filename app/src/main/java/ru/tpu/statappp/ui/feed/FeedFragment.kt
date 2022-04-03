package ru.tpu.statappp.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.tpu.statappp.R
import ru.tpu.statappp.databinding.FragmentFeedBinding
import ru.tpu.statappp.presentation.feed.FeedState
import ru.tpu.statappp.presentation.feed.FeedViewModel
import ru.tpu.statappp.ui.details.DetailsFragment
import ru.tpu.statappp.ui.feed.adapter.FeedAdapter
import ru.tpu.statappp.ui.selectdetails.SelectDetailsFragment

@AndroidEntryPoint
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

        adapter = FeedAdapter(viewModel::selectTopic, ::openDetails)
        binding?.recycler?.adapter = adapter

        viewModel.navigateToMoreEvent.observe(viewLifecycleOwner, ::openSelectDetails)
        viewModel.state.observe(viewLifecycleOwner, ::renderState)

        viewModel.loadData()
    }

    private fun renderState(state: FeedState) {
        when (state) {
            FeedState.Initial -> Unit
            FeedState.Loading -> {
                binding?.run {
                    progressBar.isVisible = true
                    recycler.isVisible = false
                }
            }
            is FeedState.Content -> {
                binding?.run {
                    progressBar.isVisible = false
                    recycler.isVisible = true
                }
                adapter?.items = state.items
            }
        }
    }

    private fun openDetails(topicName: String, name: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, DetailsFragment.newInstance(topicName, name))
            .addToBackStack(null)
            .commit()
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