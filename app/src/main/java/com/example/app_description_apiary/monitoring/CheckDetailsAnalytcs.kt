package com.example.app_description_apiary.monitoring

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class CheckDetailsAnalytcs (private val firebaseAnalytics: FirebaseAnalytics) {

    fun successCheckDetailsFirebase(){
        val details = ""
        val bundle =Bundle()
        bundle.putString("details",details)
        firebaseAnalytics.logEvent(SUCCESS_DETAILS,bundle)
    }

    fun errorCheckDetailsFirebase(){
        val details = ""
        val bundle =Bundle()
        bundle.putString("details",details)
        firebaseAnalytics.logEvent(ERROR_DETAILS,bundle)
    }

    companion object{
        const val SUCCESS_DETAILS ="SUCCESS_DETAILS"
        const val ERROR_DETAILS ="ERROR_DETAILS"
    }
}