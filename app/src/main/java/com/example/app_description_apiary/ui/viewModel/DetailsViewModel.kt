package com.example.app_description_apiary.ui.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.DetailsUser
import com.example.app_description_apiary.useCase.UserUseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException

class DetailsViewModel(private val context: Context, private val userUseCase: UserUseCase) :
    ViewModel() {
    var successLiveGetDetailsUser = MutableLiveData<List<DetailsUser>>()
    val loadLiveData = MutableLiveData<Boolean>()
    val toasLiveData = MutableLiveData<String>()
    var disposables = CompositeDisposable()


    fun getDetailsUser(id: Int) {
        loadLiveData.value = true
        disposables.add(userUseCase.getDetailsUser(id).subscribe { res, error ->
            if (error != null && error !is UnknownHostException) {
                toasLiveData.value = error.message
            }
            if (error != null && error is UnknownHostException) {
                toasLiveData.value = context.getString(R.string.error_not_connection)
            } else {
                successLiveGetDetailsUser.value = res
            }
            loadLiveData.value = false
        })
    }
}