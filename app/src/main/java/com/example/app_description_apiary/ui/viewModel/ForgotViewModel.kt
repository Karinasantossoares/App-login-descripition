package com.example.app_description_apiary.ui.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.ResetUser
import com.example.app_description_apiary.monitoring.ForgotPasswordAnalytcs
import com.example.app_description_apiary.useCase.UserUseCase
import io.reactivex.disposables.CompositeDisposable

class ForgotViewModel(
    private var context: Context,
    private val useCase: UserUseCase,
    private val analytcs: ForgotPasswordAnalytcs

) :
    ViewModel() {
    val loadLiveData = MutableLiveData<Boolean>()
    val toasLiveData = MutableLiveData<String>()
    var disposables = CompositeDisposable()
    var successLiveData = MutableLiveData<String>()
    var returToBackLiveData = MutableLiveData<Unit>()


    fun resetPassword(resetUser: ResetUser) {
        loadLiveData.value = true
        disposables.add(useCase.resetPassword(resetUser).subscribe { _, error ->
            if (error != null) {
                toasLiveData.value = error.localizedMessage
                analytcs.trackErrorForgotPasswordFirebase(resetUser.email)
            } else {
                successLiveData.value = context.getString(R.string.message_success_forgot_password)
                analytcs.trackSuccessForgotPasswordFirebase(resetUser.email)
            }
            loadLiveData.value = false
        })
    }

    fun clickOkDialog() {
        returToBackLiveData.value = null
    }




}