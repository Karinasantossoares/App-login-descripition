package com.example.app_description_apiary.persistence.preferences


import android.content.SharedPreferences

class AppPreferences(private val preferences: SharedPreferences) {

    fun saveStringKey(key: String, value: String) {
        val edit = preferences.edit()
        edit.putString(key, value)
        edit.apply()
    }

    fun saveIntColorKey(key:String,value: Int){
        val edit = preferences.edit()
        edit.putInt(key, value)
        edit.apply()
    }

    fun getStringByKey(key: String) = preferences.getString(key, null)

    fun getIntByKey(key:String) = preferences.getInt(key,0)

    companion object {
        const val NAME = "AppDescription"
        const val CPF = "CPF"
        const val PASSWORD = "PASSWORD"
        const val COLOR = "COLOR"
    }
}