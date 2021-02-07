package com.romariomkk.destinations.domain.mapper

import com.romariomkk.destinations.api.pojo.StationsResponseDTO
import com.romariomkk.destinations.data.db.pojo.DBDestination
import com.romariomkk.destinations.domain.pojo.Destination

fun StationsResponseDTO.StationDTO.toDBDestination() =
    DBDestination(code, name, countryCode, countryName)

fun DBDestination.toDestination() =
    Destination(code, name, countryCode, countryName)