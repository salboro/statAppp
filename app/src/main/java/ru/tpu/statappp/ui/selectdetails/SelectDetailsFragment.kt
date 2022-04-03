package ru.tpu.statappp.ui.selectdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.tpu.statappp.R
import ru.tpu.statappp.databinding.FragmentSelectDetailsBinding
import ru.tpu.statappp.presentation.selectdetails.SelectDetailsState
import ru.tpu.statappp.presentation.selectdetails.SelectDetailsViewModel
import ru.tpu.statappp.ui.details.DetailsFragment

@AndroidEntryPoint
class SelectDetailsFragment : Fragment() {

    companion object {

        const val TOPIC_KEY = "topicKey"

        fun newInstance(topicName: String): SelectDetailsFragment =
            SelectDetailsFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(TOPIC_KEY, topicName)
                    }
                }
    }

    private var binding: FragmentSelectDetailsBinding? = null
    private var adapter: SelectDetailsAdapter? = null
    private val viewModel: SelectDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectDetailsBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SelectDetailsAdapter(viewModel::selectDetails)

        subscribeViewModel()

        binding?.toolbar?.title = requireArguments().getString(TOPIC_KEY)
        binding?.recycler?.adapter = adapter

        binding?.toolbar?.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun subscribeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::renderState)
        viewModel.navigateToDetailsEvent.observe(viewLifecycleOwner, ::navigateToDetails)
    }

    private fun renderState(state: SelectDetailsState) {
        when (state) {
            SelectDetailsState.Initial -> Unit
            SelectDetailsState.Loading -> {
                binding?.run {
                    progressBar.isVisible = true
                    recycler.isVisible = false
                }
            }
            is SelectDetailsState.Content -> {
                binding?.run {
                    progressBar.isVisible = false
                    recycler.isVisible = true
                }
                adapter?.items = state.items
            }
        }
    }

    private fun navigateToDetails(idToName: Pair<String, String>) {
        val (id, name) = idToName
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, DetailsFragment.newInstance(id, name))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        adapter = null
        binding?.recycler?.adapter = null
        binding = null

        super.onDestroyView()
    }
}