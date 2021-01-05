package com.example.app_description_apiary.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseUser(
    val id: Int,
    val name: String,
    val favoritePerson: String,
    val urlImage:String
) :Parcelable