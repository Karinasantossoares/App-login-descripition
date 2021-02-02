package com.example.app_description_apiary.ui.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.DetailsUser
import com.example.app_description_apiary.data.ResponseUser
import com.example.app_description_apiary.monitoring.CheckDetailsAnalytcs
import com.example.app_description_apiary.persistence.preferences.AppPreferences
import com.example.app_description_apiary.useCase.UserUseCase
import com.google.firebase.analytics.FirebaseAnalytics
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException

class DetailsViewModel(
    private val context: Context,
    private val userUseCase: UserUseCase,
    private val preferences: AppPreferences,
    responseUser: ResponseUser,
    private val analytics: CheckDetailsAnalytcs
) :
    ViewModel() {
    var successLiveGetDetailsUser = MutableLiveData<List<DetailsUser>>()
    val loadLiveData = MutableLiveData<Boolean>()
    val toasLiveData = MutableLiveData<String>()
    var disposables = CompositeDisposable()
    var colorOffLiveData = MutableLiveData<Unit>()
    var colorOnLiveData = MutableLiveData<Unit>()
    var checkSwitchLiveData = MutableLiveData<Boolean>()
    var changedTextLiveData = MutableLiveData<String>()
    var changedColorTextLiveData = MutableLiveData<Int>()

    init {
        getDetailsUser(responseUser.id)
    }


    fun getDetailsUser(id: Int) {
        loadLiveData.value = true
        disposables.add(userUseCase.getDetailsUser(id).subscribe { res, error ->
            if (error != null && error !is UnknownHostException) {
                toasLiveData.value = error.message
                analytics.errorCheckDetailsFirebase()
            }
            if (error != null && error is UnknownHostException) {
                toasLiveData.value = context.getString(R.string.error_not_connection)
                analytics.errorCheckDetailsFirebase()
            } else {
                successLiveGetDetailsUser.value = res
                analytics.successCheckDetailsFirebase()
            }
            loadLiveData.value = false
        })
    }

    fun changedColor(choice: Boolean) {
        preferences.saveBooleanColorKey(AppPreferences.COLOR_CARD_FORGOT_FRAGMENT, choice)
        if (choice) {
            colorOnLiveData.value = null
            checkSwitchLiveData.value = choice
            changedTextLiveData.value = context.getString(R.string.message_off_profile)
            changedColorTextLiveData.value = context.getColor(R.color.black)
        } else {
            colorOffLiveData.value = null
            checkSwitchLiveData.value = choice
            changedTextLiveData.value = context.getString(R.string.message_on_profile)
            changedColorTextLiveData.value = context.getColor(R.color.white)

        }
    }

    fun checkColor() {
        val check = preferences.getBooleanByKey(AppPreferences.COLOR_CARD_FORGOT_FRAGMENT)
        changedColor(check)
    }

}