package com.example.app_description_apiary.repository


import com.example.app_description_apiary.data.DetailsUser
import com.example.app_description_apiary.data.RequestUser
import com.example.app_description_apiary.data.ResetUser
import com.example.app_description_apiary.data.ResponseUser
import com.example.app_description_apiary.repository.networkDto.LoginRequestDTO
import com.example.app_description_apiary.repository.networkDto.ResetLoginRequestDTO
import com.example.app_description_apiary.service.UserService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepository(
    private val service: UserService

) {

    fun getDetailsUser(id: Int) = service.getDetailsUser(id).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).map { listDetailsUserDto ->
            listDetailsUserDto.map {
                DetailsUser(it.id,it.name,it.job,it.urlImage)
            }
        }


    fun logIn(login: RequestUser): Single<ResponseUser> {
        return service.logIn(LoginRequestDTO(login.cpf,login.password)).map {
            ResponseUser(it.id, it.name, it.favoritePerson, it.urlImage)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun resetPassword(loginReset: ResetUser) = service.resetPassword(ResetLoginRequestDTO(loginReset.email))
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())


}
