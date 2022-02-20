package ru.tpu.statappp.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.tpu.statappp.databinding.FragmentFeedBinding

class FeedFragment: Fragment() {

    private var binding: FragmentFeedBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}