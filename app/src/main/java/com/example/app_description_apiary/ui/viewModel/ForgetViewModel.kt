package com.example.app_description_apiary.ui.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.ResetUser
import com.example.app_description_apiary.useCase.UserUseCase
import io.reactivex.disposables.CompositeDisposable

class ForgetViewModel(private var context: Context, private val useCase: UserUseCase) :
    ViewModel() {
    val loadLiveData = MutableLiveData<Boolean>()
    val toasLiveData = MutableLiveData<String>()
    var disposables = CompositeDisposable()
    var successLiveData = MutableLiveData<Unit>()

    fun resetPassword(resetUser: ResetUser) {
        loadLiveData.value = true
        disposables.add(useCase.resetPassword(resetUser).subscribe { res, error ->
            if (error != null) {
                toasLiveData.value = error.localizedMessage
            } else {
                toasLiveData.value = R.string.message_success_forgot_password.toString()
            }
            loadLiveData.value = false

        })
    }

}