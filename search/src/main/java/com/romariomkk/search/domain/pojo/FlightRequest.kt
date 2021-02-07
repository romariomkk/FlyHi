package com.romariomkk.search.domain.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class FlightRequest(
    val departureName: String,
    val departureCode: String,
    val arrivalName: String,
    val arrivalCode: String,
    val date: Date,
    val adults: Int,
    val teens: Int,
    val children: Int
): Parcelable