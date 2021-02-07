package com.romariomkk.search.domain.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Flight(
    var departure: String? = null,
    var destination: String? = null,
    var flightNumber: String? = null,
    var flightDate: Date? = null,
    var duration: String? = null,
    var infantsLeft: Int? = null,
    var fareClass: String? = null,
    var fare: Fare? = null
): Parcelable {

    @Parcelize
    data class Fare(
        var amount: Float,
        var discountInPercent: Int,
        var currency: String): Parcelable


    companion object {
        @JvmStatic
        fun empty() = Flight()
    }
}