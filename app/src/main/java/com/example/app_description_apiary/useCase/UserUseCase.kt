package com.example.app_description_apiary.useCase

import android.content.Context
import android.icu.text.UFormat
import android.text.TextUtils
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.*
import com.example.app_description_apiary.extensions.isEmailValid
import com.example.app_description_apiary.extensions.toDate
import com.example.app_description_apiary.repository.UserRepository
import io.reactivex.Single
import java.lang.Exception

import java.util.*


class UserUseCase(
    private val context: Context,
    private val repository: UserRepository
) {

    fun getDetailsUser(id: Int) = repository.getDetailsUser(id)

    fun logIn(login: RequestUser): Single<ResponseUser> {
        if (login.cpf.length != 11) {
            return Single.error(Exception(context.getString(R.string.message_error_cpf_invalid)))
        }
        return if (login.password.isEmpty()) {
            Single.error(Exception(context.getString(R.string.message_error_password)))
        } else {
            repository.logIn(login)
        }
    }

    fun resetPassword(loginReset: ResetUser): Single<Unit> {
        return if (!loginReset.email.isEmailValid()) {
            Single.error(Exception(context.getString(R.string.message_error_email)))
        } else {
            repository.resetPassword(loginReset)
        }
    }


    fun getStates() = repository.getStates()


    fun newRegister(newUser: RegisterUser): Single<Unit> {
        if (newUser.name.length < 3) {
            return Single.error(Exception(context.getString(R.string.message_register_name)))
        }
        if (newUser.lastName.length < 3) {
            return Single.error(Exception(context.getString(R.string.message_register_last_name)))
        }
        if (newUser.name == newUser.lastName) {
            return Single.error(Exception(context.getString(R.string.message_strings_equals)))
        }

        if (newUser.dateOfBirth.isEmpty()) {
            return Single.error(Exception(context.getString(R.string.message_error_empty)))
        }
        val date = newUser.dateOfBirth.toDate()
        if (date == null) {
            return Single.error(Exception(context.getString(R.string.message_error_date_of_birth_invalid)))
        } else if (date.time >= Date().time) {
            return Single.error(Exception(context.getString(R.string.message_error_date_future)))
        }
        val calendar = Calendar.getInstance(Locale.getDefault())
        calendar.add(Calendar.YEAR, -18)
        if (date.time >= calendar.time.time) {
            return Single.error(Exception(context.getString(R.string.message_error_minor)))
        }

        return repository.newRegister(newUser)
    }
    
}



