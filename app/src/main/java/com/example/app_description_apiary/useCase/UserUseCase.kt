package com.example.app_description_apiary.useCase

import android.content.Context
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.*
import com.example.app_description_apiary.extensions.isEmailValid
import com.example.app_description_apiary.extensions.toDate
import com.example.app_description_apiary.monitoring.AuthenticateAnalytics
import com.example.app_description_apiary.repository.UserRepository
import io.reactivex.Single
import java.lang.Exception

import java.util.*


class UserUseCase(
    private val context: Context,
    private val repository: UserRepository,
    private val analytics: AuthenticateAnalytics
) {

    fun getDetailsUser(id: Int) = repository.getDetailsUser(id)

    fun logIn(login: RequestUser): Single<ResponseUser> {
        if (login.cpf.length != 11) {
            analytics.trackErrorData()
            return Single.error(Exception(context.getString(R.string.message_error_cpf_invalid)))

        }
        return if (login.password.isEmpty()) {
            analytics.trackErrorData()
            Single.error(Exception(context.getString(R.string.message_error_password)))
        } else {
            analytics.trackSuccessData()
            repository.logIn(login)
        }
    }

    fun resetPassword(loginReset: ResetUser): Single<Unit> {
        return if (!loginReset.email.isEmailValid()) {
            analytics.trackErrorData()
            Single.error(Exception(context.getString(R.string.message_error_email)))
        } else {
            analytics.trackSuccessData()
            repository.resetPassword(loginReset)
        }
    }


    fun getStates() = repository.getStates()


    fun newRegister(newUser: RegisterUser): Single<Unit> {
        analytics.trackErrorData()
        if (newUser.name.length < 3) {
            return Single.error(Exception(context.getString(R.string.message_register_name)))
        }
        if (newUser.lastName.length < 3) {
            analytics.trackErrorData()
            return Single.error(Exception(context.getString(R.string.message_register_last_name)))
        }
        if (newUser.name == newUser.lastName) {
            analytics.trackErrorData()
            return Single.error(Exception(context.getString(R.string.message_strings_equals)))
        }

        if (newUser.dateOfBirth.isEmpty()) {
            analytics.trackErrorData()
            return Single.error(Exception(context.getString(R.string.message_error_empty)))
        }
        val date = newUser.dateOfBirth.toDate()
        if (date == null) {
            analytics.trackErrorData()
            return Single.error(Exception(context.getString(R.string.message_error_date_of_birth_invalid)))
        } else if (date.time >= Date().time) {
            analytics.trackErrorData()
            return Single.error(Exception(context.getString(R.string.message_error_date_future)))
        }
        val calendar = Calendar.getInstance(Locale.getDefault())
        calendar.add(Calendar.YEAR, -18)
        if (date.time >= calendar.time.time) {
            analytics.trackErrorData()
            return Single.error(Exception(context.getString(R.string.message_error_minor)))
        }
        analytics.trackSuccessData()
        return repository.newRegister(newUser)

    }
    
}



