package com.example.app_description_apiary.ui.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app_description_apiary.databinding.FragmentDescriptionProfileUserBinding

class DescriptionProfileUserFragment(s: String) : Fragment() {
    private lateinit var binding: FragmentDescriptionProfileUserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionProfileUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(EXTRA_NAME)?.let {
            binding.tvName.text = it
        }

    }

    companion object {
        private const val EXTRA_NAME = "EXTRA_NAME"
        fun newInstance(s: String): DescriptionProfileUserFragment {
            val args = Bundle()
            args.putString(EXTRA_NAME, s)
            val fragment = DescriptionProfileUserFragment(s)
            fragment.arguments = args
            return fragment
        }
    }
}