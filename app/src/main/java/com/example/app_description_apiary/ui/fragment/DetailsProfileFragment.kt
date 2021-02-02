package com.example.app_description_apiary.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.app_description_apiary.R
import com.example.app_description_apiary.databinding.FragmentDetailsProfileBinding
import com.example.app_description_apiary.databinding.FragmentLoginBinding


class DetailsProfileFragment : Fragment() {
    private lateinit var binding: FragmentDetailsProfileBinding
    private lateinit var mPager :ViewPager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_details_profile, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


    companion object{
        private const val NUM_PAGER = 3
    }


}