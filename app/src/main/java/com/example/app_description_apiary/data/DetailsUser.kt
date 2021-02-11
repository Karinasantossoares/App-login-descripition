package com.example.app_description_apiary.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailsUser(
    val id: Int = 0,
    val name: String,
    val job: String,
    val urlImage: String
):Parcelable






