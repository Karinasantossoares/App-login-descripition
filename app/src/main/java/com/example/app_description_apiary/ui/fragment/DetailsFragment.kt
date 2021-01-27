package com.example.app_description_apiary.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.ResponseUser
import com.example.app_description_apiary.databinding.FragmentDetailsBinding
import com.example.app_description_apiary.ui.adapter.UserAdapter
import com.example.app_description_apiary.ui.viewModel.DetailsViewModel
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel


class DetailsFragment() : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.checkColor()

        arguments?.getParcelable<ResponseUser>(RESPONSE_LOGIN_KEY)?.let { responseUser->
            viewModel.getDetailsUser(responseUser.id)
            binding.tvNamePersonTitile.text = responseUser.name
            Picasso.get().load(responseUser.urlImage).into(binding.ivPerson)
            binding.swipeContainer.setOnRefreshListener {
                viewModel.getDetailsUser(responseUser.id)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_detailsFragment_to_loginFragment)
        }

        binding.switchChangeColorBackground.setOnCheckedChangeListener { _, isChecked ->
            viewModel.changedColor(isChecked)
        }


        viewModel.checkSwitchLiveData.observe(viewLifecycleOwner, Observer {
            binding.switchChangeColorBackground.isChecked = it
        })

        viewModel.colorDarkLiveData.observe(viewLifecycleOwner, Observer {
            binding.cardViewDetails.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.perfilOn
                )
            )
        })

        viewModel.colorLightLiveData.observe(viewLifecycleOwner, Observer {
            binding.cardViewDetails.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.perfiloff
                )
            )
        })


        viewModel.loadLiveData.observe(viewLifecycleOwner, Observer {
            binding.swipeContainer.isRefreshing = it
        })

        viewModel.toasLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        viewModel.successLiveGetDetailsUser.observe(viewLifecycleOwner, Observer {
            val responseUser = arguments?.getParcelable<ResponseUser>(RESPONSE_LOGIN_KEY)
            if (responseUser != null) {
                val adapter = UserAdapter(it)
                binding.rvMyRecycler.adapter = adapter
            }
        })
    }

    companion object {
        const val RESPONSE_LOGIN_KEY = "RESPONSE_LOGIN_KEY"
    }

}