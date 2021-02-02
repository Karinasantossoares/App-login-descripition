package com.example.app_description_apiary.monitoring

import android.os.Bundle
import com.example.app_description_apiary.data.RegisterUser
import com.google.firebase.analytics.FirebaseAnalytics

class AuthenticateAnalytics(private val firebaseAnalytics: FirebaseAnalytics) {

    fun trackSuccessLoginFirebase(cpf: String) {
        val bundle = Bundle()
        bundle.putString("cpf", cpf)
        firebaseAnalytics.logEvent(SUCCESS_LOGIN, bundle)
    }

    fun trackErrorLoginFirebase(cpf: String) {
        val bundle = Bundle()
        bundle.putString("cpf", cpf)
        firebaseAnalytics.logEvent(ERROR_LOGIN, bundle)
    }


    fun trackBiometricLoginFirebase(cpf: String) {
        val bundle = Bundle()
        bundle.putString("cpf", cpf)
        firebaseAnalytics.logEvent(BIOMETRIC_LOGIN, bundle)
    }

    fun trackSuccessRegisterFirebase(name: String) {
        val bundle = Bundle()
        bundle.putString("name", name)
        firebaseAnalytics.logEvent(EVENT_REGISTER_SUCCESS, bundle)
    }

    fun trackErrorRegisterFirebase(name: String) {
        val bundle = Bundle()
        bundle.putString("name", name)
        firebaseAnalytics.logEvent(EVENT_REGISTER_ERROR, bundle)
    }

    fun openScreenLogin() {
        val login = ""
        val bundle =Bundle()
        bundle.putString("login",login)
        firebaseAnalytics.logEvent(SCREEN_LOGIN,bundle)
    }

    fun trackErrorData(){
        val data = ""
        val bundle =Bundle()
        bundle.putString("data",data)
        firebaseAnalytics.logEvent(ERROR_DATA_LOGIN,bundle)

    }
    fun trackSuccessData(){
        val data = ""
        val bundle =Bundle()
        bundle.putString("data",data)
        firebaseAnalytics.logEvent(SUCCESS_DATA_LOGIN,bundle)
    }


    companion object {
        private const val SUCCESS_LOGIN = "SUCCESS_LOGIN"
        private const val ERROR_LOGIN = "ERROR_LOGIN"
        private const val BIOMETRIC_LOGIN = "BIOMETRIC_LOGIN"
        private const val EVENT_REGISTER_SUCCESS = "EVENT_REGISTER"
        private const val EVENT_REGISTER_ERROR = "EVENT_REGISTER"
        private const val SCREEN_LOGIN = "SECREEN_LOGIN"
        private const val ERROR_DATA_LOGIN = "ERROR_DATA_LOGIN"
        private const val SUCCESS_DATA_LOGIN = "SUCCESS_DATA_LOGIN"

    }


}




