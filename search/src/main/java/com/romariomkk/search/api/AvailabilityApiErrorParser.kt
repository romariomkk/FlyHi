package com.romariomkk.search.api

import com.romariomkk.core.util.ErrorParser
import com.romariomkk.search.util.Keys
import retrofit2.HttpException
import javax.inject.Inject

class AvailabilityApiErrorParser @Inject constructor(): ErrorParser {

    override fun parse(t: Throwable): String {
        return if (t is HttpException) {
            when (t.code()) {
                403 -> Keys.Errors.TOS_NOT_AGREED
                404 -> Keys.Errors.NO_FLIGHT_BETWEEN_DESTINATIONS
                else -> t.message()
            }
        } else {
            t.message ?: Keys.Errors.GENERAL_ERROR
        }
    }
}