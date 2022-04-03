package ru.tpu.statappp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint
import ru.tpu.statappp.R
import ru.tpu.statappp.databinding.FragmentDetailsBinding
import ru.tpu.statappp.domain.entity.DateResolution
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

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initChart()
        initListeners()

        viewModel.state.observe(viewLifecycleOwner, ::renderState)

        binding?.run {
            favoriteImage.setOnClickListener {
                viewModel.changeFavoriteStatus()
            }

            toolbar.title = requireArguments().getString(NAME_KEY)
            toolbar.setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }
            chartResolutionTwoWeeks.toggle()
        }
    }

    private fun initChart() {
        binding?.chart?.run {
            legend.isEnabled = false
            description.isEnabled = false
            xAxis.setDrawGridLines(false)
            xAxis.setLabelCount(2, true)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
        }
    }

    private fun initListeners() {
        binding?.chartResolutionGroup?.addOnButtonCheckedListener { _, checkedId, _ ->
            val resolution = when (checkedId) {
                R.id.chartResolutionTwoWeeks -> DateResolution.TWO_WEEKS
                R.id.chartResolutionOneMonth -> DateResolution.ONE_MONTH
                R.id.chartResolutionSixMonth -> DateResolution.SIX_MONTH
                R.id.chartResolutionOneYear -> DateResolution.ONE_YEAR
                else -> throw IllegalArgumentException("Unknown dateResolution for id: $checkedId")
            }

            viewModel.resolution.value = resolution
        }
    }

    private fun renderState(state: DetailsState) {
        when (state) {
            DetailsState.Initial -> Unit
            DetailsState.Loading -> {
                binding?.run {
                    favoriteImage.isVisible = false
                    progressBar.isVisible = true
                    content.isVisible = false
                }
            }
            is DetailsState.Content -> {
                binding?.run {
                    favoriteImage.isVisible = true
                    progressBar.isVisible = false
                    content.isVisible = true

                    val entries = state.data.entries.mapIndexed { index, entry ->
                        Entry(index.toFloat(), entry.value.toFloat(), entry.key)
                    }
                    val dataSet = LineDataSet(entries, "")
                    chart.xAxis.valueFormatter = DetailsChartLabelFormatter(dataSet)
                    chart.data = LineData(dataSet)

                    if (state.favorite) {
                        favoriteImage.setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.ic_baseline_favorite_24
                            )
                        )
                    } else {
                        favoriteImage.setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.ic_baseline_favorite_border_24
                            )
                        )
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}