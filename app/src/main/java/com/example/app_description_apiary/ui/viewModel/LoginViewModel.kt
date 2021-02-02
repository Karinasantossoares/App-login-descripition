package com.example.app_description_apiary.ui.viewModel

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.RequestUser
import com.example.app_description_apiary.data.ResponseUser
import com.example.app_description_apiary.monitoring.AuthenticateAnalytics
import com.example.app_description_apiary.persistence.preferences.AppPreferences
import com.example.app_description_apiary.useCase.UserUseCase
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException

class LoginViewModel(
    private val context: Context,
    private val userUseCase: UserUseCase,
    private val preferences: AppPreferences,
    private val analytics: AuthenticateAnalytics
) : ViewModel() {

    init {
        analytics.openScreenLogin()
    }

    var disposables = CompositeDisposable()
    val loadLiveData = MutableLiveData<Boolean>()
    var successLiveDataLogin = MutableLiveData<ResponseUser>()
    val messageErrorLiveData = MutableLiveData<String>()
    val checkDeviceLiveData = MutableLiveData<Boolean>()

    fun loginWithViewModel() {
        val cpf = preferences.getStringByKey(AppPreferences.CPF) ?: ""
        val password = preferences.getStringByKey(AppPreferences.PASSWORD) ?: ""
        val requestUser = RequestUser(cpf, password)
        logIn(requestUser)
        analytics.trackBiometricLoginFirebase(requestUser.cpf)
    }

    fun logIn(login: RequestUser) {
        loadLiveData.value = true
        disposables.add(userUseCase.logIn(login).subscribe { res, error ->
            if (error != null && error !is UnknownHostException) {
                messageErrorLiveData.value = error.localizedMessage
                analytics.trackErrorLoginFirebase(login.cpf)
            } else if (error != null && error is UnknownHostException) {
                messageErrorLiveData.value = context.getString(R.string.error_not_connection)
                analytics.trackErrorLoginFirebase(login.cpf)
            } else {
                preferences.saveStringKey(AppPreferences.CPF, login.cpf)
                preferences.saveStringKey(AppPreferences.PASSWORD, login.password)
                successLiveDataLogin.value = res
                analytics.trackSuccessLoginFirebase(login.cpf)
            }
            loadLiveData.value = false
        })
    }

    fun checkBiometricDevice(biometricManager: BiometricManager) {
        checkDeviceLiveData.value =
            biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS && preferences.getStringByKey(
                AppPreferences.CPF
            ) != null
    }

}










