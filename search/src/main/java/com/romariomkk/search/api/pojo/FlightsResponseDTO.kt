package com.romariomkk.search.api.pojo

data class FlightsResponseDTO(
    val currency: String,
    val trips: List<TripDTO>
)

data class TripDTO(
    val originName: String,
    val destinationName: String,
    val dates: List<FlightsForDateDTO>
)

data class FlightsForDateDTO(
    val dateOut: String,
    val flights: List<FlightDTO>
)

data class FlightDTO(
    val faresLeft: Int,
    val flightNumber: String,
    val infantsLeft: Int,
    val duration: String,
    val regularFare: RegularFareDTO
)

data class RegularFareDTO(
    val fareClass: String,
    val fares: List<FareDTO>
)

data class FareDTO(
    val type: String,
    val amount: Float,
    val hasDiscount: Boolean,
    val publishedFare: Float,
    val discountInPercent: Int
)