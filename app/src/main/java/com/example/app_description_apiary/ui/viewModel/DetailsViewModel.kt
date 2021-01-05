package com.example.app_description_apiary.ui.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_description_apiary.data.DetailsUser
import com.example.app_description_apiary.useCase.UserUseCase
import io.reactivex.disposables.CompositeDisposable

class DetailsViewModel(private val context: Context, private val userUseCase: UserUseCase) :
    ViewModel() {
    var successLiveGetDetailsUser = MutableLiveData<List<DetailsUser>>()
    val loadLiveData = MutableLiveData<Boolean>()
    val toasLiveData = MutableLiveData<String>()
    var disposables = CompositeDisposable()


    fun getDetailsUser(id: Int) {
        loadLiveData.value = true
        disposables.add(userUseCase.getDetailsUser(id).subscribe { res, error ->
            if (error != null) {
                toasLiveData.value = error.message
            } else {
                successLiveGetDetailsUser.value = res
            }
            loadLiveData.value = false
        })
    }
}