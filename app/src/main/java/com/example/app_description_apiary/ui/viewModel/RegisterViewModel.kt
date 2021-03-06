package com.example.app_description_apiary.ui.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.RegisterUser
import com.example.app_description_apiary.data.Sigla
import com.example.app_description_apiary.monitoring.AuthenticateAnalytics
import com.example.app_description_apiary.useCase.UserUseCase
import com.google.firebase.analytics.FirebaseAnalytics
import io.reactivex.disposables.CompositeDisposable

class RegisterViewModel(
    private val context: Context,
    private val userUseCase: UserUseCase,
    private val analytics: AuthenticateAnalytics
) :
    ViewModel() {

    val successLiveData = MutableLiveData<String>()
    val loadLiveData = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
    val backScreenLiveData = MutableLiveData<Unit>()
    val messageErrorLiveData = MutableLiveData<String>()
    var successLiveGetStates = MutableLiveData<List<Sigla>>()

    fun newRegister(newUser: RegisterUser) {
        loadLiveData.value = true
        disposable.add(userUseCase.newRegister(newUser).subscribe { res, error ->
            if (res != null) {
                successLiveData.value = context.getString(R.string.message_register_ok)
                analytics.trackSuccessRegisterFirebase(newUser.name)
            } else {
                messageErrorLiveData.value = error.localizedMessage
                analytics.trackErrorRegisterFirebase(newUser.name)
            }
            loadLiveData.value = false
        })
    }

    fun screenLogin() {
        backScreenLiveData.value = null
    }

    fun getStates() {
        disposable.add(userUseCase.getStates().subscribe { res, _ ->
            if (res != null) {
                successLiveGetStates.value = res
            }
        })
    }

}




