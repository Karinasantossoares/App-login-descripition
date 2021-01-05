package com.example.app_description_apiary.service

import com.example.app_description_apiary.repository.networkDto.DetailsUserDTO
import com.example.app_description_apiary.repository.networkDto.ResetLoginRequestDTO
import com.example.app_description_apiary.repository.networkDto.LoginRequestDTO
import com.example.app_description_apiary.repository.networkDto.LoginResponseDTO
import io.reactivex.Single
import retrofit2.http.*

interface UserService {

    @GET("details/idUser")
    fun getUser(@Path("idUser") id: Int): Single<List<DetailsUserDTO>>


    @POST("login")
    fun logIn(@Body loginRequestDTO:LoginRequestDTO): Single<LoginResponseDTO>


    @POST("forgotPassword")
    fun resetPassword(@Body resetLoginDto: ResetLoginRequestDTO): Single<Unit>
}