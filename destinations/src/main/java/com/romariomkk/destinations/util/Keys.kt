package com.romariomkk.destinations.util

import com.romariomkk.destinations.R

object Keys {

    const val KEY_DESTINATION_RESULT = "destintion_result"
    const val KEY_FROM_OR_TO = "from_or_to"
    const val KEY_DESTINATION = "destination"

    @JvmStatic
    val ERROR_MAP = mapOf(
        Errors.ERR_FAILED_TO_RETRIEVE to R.string.error_connection
    )

    object Errors {
        const val ERR_FAILED_TO_RETRIEVE = "${0x0001}"
    }
}