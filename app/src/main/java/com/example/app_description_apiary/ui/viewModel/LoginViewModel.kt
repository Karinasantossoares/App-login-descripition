package com.example.app_description_apiary.ui.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.RequestUser
import com.example.app_description_apiary.data.ResponseUser
import com.example.app_description_apiary.useCase.UserUseCase
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException

class LoginViewModel(private val context: Context, private val userUseCase: UserUseCase) :
    ViewModel() {

    var disposables = CompositeDisposable()
    val loadLiveData = MutableLiveData<Boolean>()
    var successLiveDataLogin = MutableLiveData<ResponseUser>()
    val messageErrorLiveData = MutableLiveData<String>()

    fun logIn(login: RequestUser) {
        loadLiveData.value = true
        disposables.add(userUseCase.logIn(login).subscribe { res, error ->
            if (error != null && error !is UnknownHostException) {
                messageErrorLiveData.value = error.localizedMessage
            } else if (error != null && error is UnknownHostException) {
                messageErrorLiveData.value = context.getString(R.string.error_not_connection)
            } else {
                successLiveDataLogin.value = res
            }
            loadLiveData.value = false
        })
    }
}
