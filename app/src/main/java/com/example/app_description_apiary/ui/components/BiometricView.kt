package com.example.app_description_apiary.ui.components

import android.content.Context
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.app_description_apiary.R


class BiometricView(

    private val fragment: Fragment
) {
    private lateinit var biometricPrompt: BiometricPrompt
    private val executor = ContextCompat.getMainExecutor(fragment.requireContext())

    fun showDialogView(): BiometricPrompt.PromptInfo =
        BiometricPrompt.PromptInfo.Builder()
            .setTitle(fragment.requireContext().getString(R.string.message_title_biometric))
            .setDescription(
                fragment.requireContext().getString(R.string.message_description_biometric_dialog)
            )
            .setNegativeButtonText(
                fragment.requireContext().getString(R.string.message_enter_login_password_not_save)
            )
            .build()

    fun authenticantionLogin(successCallBack: () -> Unit,
                              errorCallback: () -> Unit,) {
        biometricPrompt = BiometricPrompt(fragment, executor, object :
            BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                errorCallback.invoke()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                successCallBack.invoke()
            }
        })
        biometricPrompt.authenticate(showDialogView())
    }


}