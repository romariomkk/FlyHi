package com.romariomkk.destinations.api.pojo

data class StationsResponseDTO(val stations: List<StationDTO>) {

    data class StationDTO(
        val code: String,
        val name: String,
        val countryCode: String,
        val countryName: String
    )
}