package com.example.app_description_apiary.persistence.preferences


import android.content.SharedPreferences

class AppPreferences(private val preferences: SharedPreferences) {

    fun saveStringKey(key: String, value: String) {
        val edit = preferences.edit()
        edit.putString(key, value)
        edit.apply()
    }

    fun saveBooleanColorKey(key:String,value: Boolean){
        val edit = preferences.edit()
        edit.putBoolean(key,value)
        edit.apply()
    }

    fun getStringByKey(key: String) = preferences.getString(key, null)

    fun getBooleanByKey(key:String) = preferences.getBoolean(key,false)

    companion object {
        const val NAME = "AppDescription"
        const val CPF = "CPF"
        const val PASSWORD = "PASSWORD"
        const val COLOR_CARD_FORGOT_FRAGMENT = "COLOR"
    }
}