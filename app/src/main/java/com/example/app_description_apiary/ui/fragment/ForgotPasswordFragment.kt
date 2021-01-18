package com.example.app_description_apiary.ui.fragment.fragment


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.ResetUser
import com.example.app_description_apiary.databinding.FragmentForgotPasswordBinding
import com.example.app_description_apiary.ui.viewModel.ForgotViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding
    private val viewModel: ForgotViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_forgot_password, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmit.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val resetUser = ResetUser(email)
            viewModel.resetPassword(resetUser)

        }

        viewModel.toasLiveData.observe(this.viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        viewModel.successLiveData.observe(viewLifecycleOwner, Observer {
            AlertDialog.Builder(requireContext())
                .setMessage(it)
                .setCancelable(false)
                .setPositiveButton(R.string.message_ok) { _, _ ->
                    viewModel.clickOkDialog()
                }.create().show()
        })

        viewModel.returToBackLiveData.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack()
        })
    }
}