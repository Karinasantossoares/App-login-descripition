package com.example.app_description_apiary.ui.fragment.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.RequestUser
import com.example.app_description_apiary.data.ResponseUser
import com.example.app_description_apiary.databinding.FragmentLoginBinding
import com.example.app_description_apiary.ui.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEnter.setOnClickListener {
            val cpf = et_cpf.text.toString()
            val password = et_password.text.toString()
            val requestUser = RequestUser(cpf, password)
            viewModel.logIn(requestUser)
        }

        viewModel.loadLiveData.observe(this.viewLifecycleOwner, Observer {
            binding.pbLoad.isVisible = it
        })

        viewModel.successLiveDataLogin.observe(this.viewLifecycleOwner, Observer {
            val bundle = bundleOf(DetailsFragment.RESPONSE_LOGIN_KEY to it)
            view.findNavController()
                .navigate(R.id.action_loginFragment_to_detailsFragment, bundle)
        })
    }
}


