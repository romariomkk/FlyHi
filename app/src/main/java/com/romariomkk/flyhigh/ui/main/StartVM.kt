package com.romariomkk.flyhigh.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romariomkk.core.util.LiveEvent
import com.romariomkk.destinations.domain.pojo.Destination
import com.romariomkk.destinations.domain.usecase.contract.RetrieveAllDestinationsUseCase
import com.romariomkk.destinations.ui.DestinationDirection
import com.romariomkk.search.domain.pojo.FlightRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class StartVM @Inject constructor(
    private val retrieveAllDestinationsUseCase: RetrieveAllDestinationsUseCase
) : ViewModel() {
    val departure = MutableLiveData<Destination>()
    val arrival = MutableLiveData<Destination>()
    val date = MutableLiveData<Date>()

    val adults = MutableLiveData(2)
    val teens = MutableLiveData(0)
    val children = MutableLiveData(0)

    val areAirportsSelected = MutableLiveData<Boolean>()
    val areAirportsAndDateSelected = MutableLiveData<Boolean>()

    val flightRequest = LiveEvent<FlightRequest>()

    private val destinationObserver = Observer<Destination> { verifyAirportsInfoValid() }
    private val dateObserver = Observer<Date> { verifyAirportsAndDateValid() }

    init {
        viewModelScope.launch { retrieveAllDestinationsUseCase.retrieveAll() }

        arrayOf(departure, arrival).forEach { it.observeForever(destinationObserver) }
        date.observeForever(dateObserver)
    }

    fun setDestination(direction: DestinationDirection, destination: Destination) {
        if (direction == DestinationDirection.FROM) {
            departure.value = destination
        } else {
            arrival.value = destination
        }
    }

    private fun verifyAirportsInfoValid() {
        areAirportsSelected.value = areAirportsValid()
    }

    private fun verifyAirportsAndDateValid() {
        areAirportsAndDateSelected.value = areAirportsValid() && date.value != null
    }

    private fun areAirportsValid() =
        departure.value != null && arrival.value != null


    override fun onCleared() {
        super.onCleared()
        arrayOf(departure, arrival).forEach { it.removeObserver(destinationObserver) }
        date.removeObserver(dateObserver)
    }

    fun collectFlightRequest() {
        val departure = departure.value
        val arrival = arrival.value
        val date = date.value
        val adults = adults.value
        val teens = teens.value
        val children = children.value
        if (departure != null && arrival != null && date != null && adults != null && teens != null && children != null) {
            flightRequest.value =
                FlightRequest(departure.name, departure.code, arrival.name, arrival.code, date, adults, teens, children)
        } else {
            //maybe show some error
        }
    }
}