package com.example.app_description_apiary.ui.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.RequestUser
import com.example.app_description_apiary.data.ResponseUser
import com.example.app_description_apiary.useCase.UserUseCase
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(private val context: Context, private val userUseCase: UserUseCase) :
    ViewModel() {

    var disposables = CompositeDisposable()
    val loadLiveData = MutableLiveData<Boolean>()
    var successLiveDataLogin = MutableLiveData<ResponseUser>()
    val toasLiveData = MutableLiveData<String>()

    fun logIn(login: RequestUser) {
        loadLiveData.value = true
        disposables.add(userUseCase.logIn(login).subscribe { res, error ->
            if (error != null) {
                toasLiveData.value = error.localizedMessage
            } else {
                successLiveDataLogin.value = res
            }
            loadLiveData.value = false
        })
    }
}
