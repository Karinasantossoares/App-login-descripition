package com.example.app_description_apiary.useCase

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import com.example.app_description_apiary.R
import com.example.app_description_apiary.data.RequestUser
import com.example.app_description_apiary.data.ResetUser
import com.example.app_description_apiary.data.ResponseUser
import com.example.app_description_apiary.repository.UserRepository
import com.example.app_description_apiary.repository.networkDto.LoginRequestDTO
import com.example.app_description_apiary.repository.networkDto.ResetLoginRequestDTO
import io.reactivex.Single
import java.lang.Exception
import kotlin.coroutines.coroutineContext


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
        if (!Patterns.EMAIL_ADDRESS.matcher(loginReset.email).matches()) {
            return Single.error(Exception(context.getString(R.string.message_error_email)))
        } else {
            return repository.resetPassword(loginReset)
        }
    }

}


