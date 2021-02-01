package com.example.app_description_apiary.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.app_description_apiary.R
import com.example.app_description_apiary.databinding.FragmentDetailsProfileBinding
import com.example.app_description_apiary.databinding.FragmentLoginBinding


class DetailsProfileFragment : Fragment() {
    private lateinit var binding: FragmentDetailsProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_details_profile, container, false)
        return binding.root
    }


}