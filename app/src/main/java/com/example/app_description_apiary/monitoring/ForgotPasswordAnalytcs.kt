package com.example.app_description_apiary.monitoring

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class ForgotPasswordAnalytcs (private val firebaseAnalytics: FirebaseAnalytics){

    fun trackSuccessForgotPasswordFirebase(email :String){
       val bundle =Bundle()
        bundle.putString("email",email)
        firebaseAnalytics.logEvent(FORGOT_PASSWORD_SUCCESS,bundle)
    }

    fun trackErrorForgotPasswordFirebase(email :String){
        val bundle =Bundle()
        bundle.putString("email",email)
        firebaseAnalytics.logEvent(FORGOT_PASSWORD_ERROR,bundle)
    }

    companion object{
        const val FORGOT_PASSWORD_SUCCESS ="FORGOT_PASSWORD_SUCCESS"
        const val FORGOT_PASSWORD_ERROR ="FORGOT_PASSWORD_ERROR"
    }
}