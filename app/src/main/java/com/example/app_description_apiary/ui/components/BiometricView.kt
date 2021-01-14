package com.example.app_description_apiary.ui.biometric

import android.content.Context
import android.provider.Settings.Secure.getString
import androidx.biometric.BiometricPrompt
import com.example.app_description_apiary.R
import javax.security.auth.callback.Callback

class BiometricView (success : BiometricPrompt.AuthenticationCallback , error:BiometricPrompt.AuthenticationCallback , context: Context) {

   fun showDialogView (message:String){
       BiometricPrompt.PromptInfo.Builder()
           .setTitle(message[R.string.message_title_biometric].toString())
           .setDescription(message)
           .setNegativeButtonText(message)
           .build()
   }
}