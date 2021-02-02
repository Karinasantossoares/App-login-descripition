package com.example.app_description_apiary.firebase

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

class FirebaseAuthenticate {


    fun loginFirebase(cpf: String, password: String) {
        val firebaseAnalytics = Firebase.analytics
        val bundle = Bundle()
        bundle.putString("cpf", cpf)
        bundle.putString("password", password)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle)

    }


}




