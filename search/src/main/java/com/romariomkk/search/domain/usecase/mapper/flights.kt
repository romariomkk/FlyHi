package com.romariomkk.search.domain.usecase.mapper

import com.romariomkk.search.api.pojo.FareDTO
import com.romariomkk.search.api.pojo.FlightsResponseDTO
import com.romariomkk.search.domain.pojo.Flight
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.jvm.Throws

@Throws(ParseException::class)
fun FlightsResponseDTO.toFlights(): List<Flight> {
    val flightDTODateOutFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ROOT)

    val currency = this.currency
    val trip = trips[0]
    val tripDate = trip.dates[0]
    return tripDate.flights
        .filter { flightDTO -> flightDTO.faresLeft != -1 }
        .mapTo(mutableListOf(), { flightDTO ->
            Flight().apply {
                departure = trip.originName
                destination = trip.destinationName
                flightNumber = flightDTO.flightNumber
                flightDate = flightDTODateOutFormat.parse(tripDate.dateOut)
                duration = flightDTO.duration
                infantsLeft = flightDTO.infantsLeft
                fareClass = flightDTO.regularFare.fareClass
                fare = flightDTO.regularFare.fares.first().toFlightFare(currency)
            }
        })
}

private fun FareDTO.toFlightFare(currency: String) =
    Flight.Fare(amount, discountInPercent, currency)