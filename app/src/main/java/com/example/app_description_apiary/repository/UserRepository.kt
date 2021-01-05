package com.example.app_description_apiary.repository


import com.example.app_description_apiary.data.User
import com.example.app_description_apiary.data.UserDescription
import com.example.app_description_apiary.repository.networkDto.ResetLoginRequestDTO
import com.example.app_description_apiary.repository.networkDto.LoginRequestDTO
import com.example.app_description_apiary.service.UserService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepository(
    private val service: UserService,

    ) {

    fun getUser(id: Int) = service.getUser(id).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).map { listDetailsUserDto ->
            listDetailsUserDto.map {
                User(it.id, it.name, it.job, it.urlImage)
            }
        }


    fun logIn(login: LoginRequestDTO) = service.logIn(login).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).map {
            UserDescription(it.id, it.name, it.favoritePerson, it.urlImage)
        }

    fun resetPassword(loginReset: ResetLoginRequestDTO) = service.resetPassword(loginReset)
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())


}
