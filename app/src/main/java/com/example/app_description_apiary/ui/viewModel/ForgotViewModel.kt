package com.example.app_description_apiary.ui.viewModel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.ResetUser
import com.example.app_description_apiary.persistence.preferences.AppPreferences
import com.example.app_description_apiary.useCase.UserUseCase
import io.reactivex.disposables.CompositeDisposable

class ForgotViewModel(private var context: Context, private val useCase: UserUseCase) :
    ViewModel() {
    val loadLiveData = MutableLiveData<Boolean>()
    val toasLiveData = MutableLiveData<String>()
    var disposables = CompositeDisposable()
    var successLiveData = MutableLiveData<String>()
    var returToBackLiveData = MutableLiveData<Unit>()
    var preferencesMutaLiveData = MutableLiveData<AppPreferences>()


    fun resetPassword(resetUser: ResetUser) {
        loadLiveData.value = true
        disposables.add(useCase.resetPassword(resetUser).subscribe { res, error ->
            if (error != null) {
                toasLiveData.value = error.localizedMessage
            } else {
                successLiveData.value = context.getString(R.string.message_success_forgot_password)
            }
            loadLiveData.value = false
        })
    }

    fun clickOkDialog() {
        returToBackLiveData.value = null
    }



}