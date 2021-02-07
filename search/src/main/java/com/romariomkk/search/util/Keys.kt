package com.romariomkk.search.util

import com.romariomkk.search.R

object Keys {

    @JvmStatic
    val ERROR_MAP = mapOf(
        Errors.NO_FLIGHT_BETWEEN_DESTINATIONS to R.string.error_no_direct_flights
    )

    object Errors {
        const val GENERAL_ERROR = "${0x001}"
        const val NO_FLIGHT_BETWEEN_DESTINATIONS = "${0x002}"
        const val TOS_NOT_AGREED = "${0x003}"
    }
}