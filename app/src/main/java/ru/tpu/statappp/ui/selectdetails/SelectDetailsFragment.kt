package ru.tpu.statappp.ui.selectdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.tpu.statappp.databinding.FragmentSelectDetailsBinding
import ru.tpu.statappp.domain.entity.SelectDetail
import ru.tpu.statappp.presentation.selectdetails.SelectDetailsViewModel

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

        binding?.recycler?.adapter = adapter
    }

    override fun onDestroyView() {
        adapter = null
        binding?.recycler?.adapter = null
        binding = null

        super.onDestroyView()
    }
}