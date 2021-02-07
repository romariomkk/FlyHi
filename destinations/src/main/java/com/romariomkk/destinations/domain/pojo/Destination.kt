package com.romariomkk.destinations.domain.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Destination(
    val code: String,
    val name: String,
    val countryCode: String,
    val countryName: String
): Parcelable {

    companion object {
        @JvmStatic
        fun empty() =
            Destination("", "", "", "")
    }
}