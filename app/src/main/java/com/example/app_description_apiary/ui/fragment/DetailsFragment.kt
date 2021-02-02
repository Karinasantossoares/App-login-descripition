package com.example.app_description_apiary.ui.fragment


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
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
import org.koin.core.parameter.parametersOf


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val responseUser by lazy {
        arguments?.getParcelable<ResponseUser>(RESPONSE_LOGIN_KEY)
    }
    private val viewModel: DetailsViewModel by viewModel {
        parametersOf(responseUser)
    }


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
        binding.tvNamePersonTitile.text = responseUser?.name
        Picasso.get().load(responseUser?.urlImage).into(binding.ivPerson)
        binding.swipeContainer.setOnRefreshListener {
            responseUser?.id?.let {
                viewModel.getDetailsUser(it)
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

        viewModel.colorOnLiveData.observe(viewLifecycleOwner, Observer {
            binding.cardViewDetails.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.perfilOn
                )
            )
        })

        viewModel.changedColorTextLiveData.observe(viewLifecycleOwner, Observer {
            binding.tvPerfilActivity.setTextColor(it)
            binding.tvNamePersonTitile.setTextColor(it)
        })

        viewModel.changedTextLiveData.observe(viewLifecycleOwner, Observer {
            binding.tvPerfilActivity.text = it
        })


        viewModel.colorOffLiveData.observe(viewLifecycleOwner, Observer {
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