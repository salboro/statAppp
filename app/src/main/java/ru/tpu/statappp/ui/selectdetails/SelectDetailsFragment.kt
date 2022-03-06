package ru.tpu.statappp.ui.selectdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        private const val TOPIC_KEY = "topicKey"

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

        binding?.recycler?.adapter = adapter
    }

    private fun subscribeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::renderState)
        viewModel.navigateToDetailsEvent.observe(viewLifecycleOwner, ::navigateToDetails)
    }

    private fun renderState(state: SelectDetailsState) {}

    private fun navigateToDetails(id: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, DetailsFragment.newInstance(id))
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