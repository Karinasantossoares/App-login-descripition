package com.example.app_description_apiary.service

import com.example.app_description_apiary.repository.networkDto.*
import io.reactivex.Single
import retrofit2.http.*

interface UserService {

    @GET("details/{idUser}")
    fun getDetailsUser(@Path("idUser") id: Int): Single<List<DetailsUserDTO>>

    @GET("states")
    fun getStates(): Single<List<SiglaDTO>>


    @POST("login")
    fun logIn(@Body loginRequestDTO: LoginRequestDTO): Single<LoginResponseDTO>


    @POST("forgotPassword")
    fun resetPassword(@Body resetLoginDto: ResetLoginRequestDTO): Single<Unit>

    @POST(value = "newRegister")
    fun newRegister(@Body registerRequestDTO: RegisterRequestDTO): Single<Unit>


}