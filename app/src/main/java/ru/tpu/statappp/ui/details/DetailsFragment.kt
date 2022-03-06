package ru.tpu.statappp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.tpu.statappp.databinding.FragmentDetailsBinding
import ru.tpu.statappp.presentation.details.DetailsViewModel

class DetailsFragment : Fragment() {

    companion object {

        private const val ID_KEY = "ID_KEY"

        fun newInstance(id: String): Fragment =
            DetailsFragment().apply {
                arguments = bundleOf(ID_KEY to id)
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

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}