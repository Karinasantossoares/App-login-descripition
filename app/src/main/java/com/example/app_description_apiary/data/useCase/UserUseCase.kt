package com.example.app_description_apiary.data.useCase

import android.widget.Toast
import com.example.app_description_apiary.repository.UserRepository
import com.example.app_description_apiary.repository.networkDto.LoginRequestDTO
import com.example.app_description_apiary.repository.networkDto.ResetLoginRequestDTO
import kotlin.coroutines.coroutineContext


class UserUseCase (
    private val repository: UserRepository
){

    fun getUser (id:Int) = repository.getUser(id)

    fun logIn(login : LoginRequestDTO) = repository.logIn(login)

    fun resetPassword(loginReset: ResetLoginRequestDTO) = repository.resetPassword(loginReset)

   }

