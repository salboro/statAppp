package ru.tpu.statappp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint
import ru.tpu.statappp.databinding.FragmentDetailsBinding
import ru.tpu.statappp.presentation.details.DetailsState
import ru.tpu.statappp.presentation.details.DetailsViewModel

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    companion object {

        const val TOPIC_KEY = "TOPIC_KEY"
        const val NAME_KEY = "NAME_KEY"

        fun newInstance(topicKey: String, nameKey: String): Fragment =
            DetailsFragment().apply {
                arguments = bundleOf(
                    TOPIC_KEY to topicKey,
                    NAME_KEY to nameKey
                )
            }
    }

    private val viewModel: DetailsViewModel by viewModels()
    private var binding: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding?.chart?.data = LineData(
            LineDataSet(
                listOf(Entry(1f, 1f)), "GOvno"
            )
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, ::renderState)

        binding?.toolbar?.title = requireArguments().getString(NAME_KEY)
        binding?.toolbar?.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun renderState(state: DetailsState) {
        when (state) {
            DetailsState.Initial -> Unit
            DetailsState.Loading -> {
                binding?.run {
                    progressBar.isVisible = true
                    content.isVisible = false
                }
            }
            is DetailsState.Content -> {
                binding?.run {
                    progressBar.isVisible = true
                    content.isVisible = false
                }
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}