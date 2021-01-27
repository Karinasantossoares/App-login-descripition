package com.example.app_description_apiary.ui.fragment


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.RegisterUser
import com.example.app_description_apiary.data.Sigla
import com.example.app_description_apiary.databinding.FragmentRegisterBinding
import com.example.app_description_apiary.extensions.setOnClickLeft
import com.example.app_description_apiary.extensions.toText
import com.example.app_description_apiary.ui.components.DateDialog
import com.example.app_description_apiary.ui.viewModel.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_register.*
import org.koin.android.viewmodel.ext.android.viewModel


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getStates()
        val dateDialog = DateDialog(requireContext())



        binding.btnRegister.setOnClickListener {
            val name = et_name_person.text.toString()
            val lastName = et_surname.text.toString()
            val dateOfBirth = et_date_of_birth.text.toString()
            val registerUser = RegisterUser(name, lastName, dateOfBirth)
            viewModel.newRegister(registerUser)
        }


        binding.etDateOfBirth.setOnClickLeft {
            dateDialog.showCalendar()
        }


        dateDialog.setOnSelectedDate {
            binding.etDateOfBirth.setText(it.toText())
        }

        viewModel.successLiveGetStates.observe(viewLifecycleOwner, Observer { listUfState ->
            val spinnerArrayAdapter: ArrayAdapter<Sigla> =
                ArrayAdapter<Sigla>(
                    requireContext(),
                    R.layout.support_simple_spinner_dropdown_item,
                    listUfState
                )
            binding.spinner.adapter = spinnerArrayAdapter
        })

        viewModel.successLiveData.observe(viewLifecycleOwner, Observer {
            AlertDialog.Builder(requireContext())
                .setMessage(it)
                .setCancelable(false)
                .setPositiveButton(R.string.message_ok) { _, _ ->
                    viewModel.scrrenLogin()
                }.create().show()
        })

        viewModel.messageErrorLiveData.observe(viewLifecycleOwner, Observer {
            AlertDialog.Builder(requireContext())
                .setMessage(it)
                .setCancelable(false)
                .setPositiveButton(R.string.message_ok) { _, _ ->
                }.create().show()
        })

        viewModel.loadLiveData.observe(viewLifecycleOwner, Observer {
            binding.pbLoadRegister.isVisible = it
        })

        viewModel.backScreenLiveData.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack()
        })
    }

}
