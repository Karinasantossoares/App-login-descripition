package com.example.app_description_apiary.extensions

import android.util.Patterns


fun String.isEmailValid() = Patterns.EMAIL_ADDRESS.matcher(this).matches()