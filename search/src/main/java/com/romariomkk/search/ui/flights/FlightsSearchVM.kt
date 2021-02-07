package com.romariomkk.search.ui.flights

import androidx.lifecycle.*
import com.romariomkk.core.util.Resource
import com.romariomkk.search.domain.pojo.Flight
import com.romariomkk.search.domain.pojo.FlightRequest
import com.romariomkk.search.domain.usecase.contract.SearchFlightsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.launch

class FlightsSearchVM @AssistedInject constructor(
    private val searchFlightsUseCase: SearchFlightsUseCase,
    @Assisted private val flightRequest: FlightRequest
) : ViewModel() {

    val maxPrice = MutableLiveData(150)
    val flights: LiveData<Resource<List<Flight>>>
        get() = _flights

    private val _flights = MutableLiveData<Resource<List<Flight>>>()

    init {
        searchFlights()
    }

    fun searchFlights() {
        viewModelScope.launch {
            _flights.value = Resource.loading()
            _flights.value = searchFlightsUseCase.search(flightRequest, maxPrice.value!!)
        }
    }

    @AssistedFactory
    @FragmentScoped
    interface Factory {
        fun create(flightRequest: FlightRequest): FlightsSearchVM
    }

    companion object {
        fun provideFactory(assistedFactory: Factory, flightRequest: FlightRequest) =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return assistedFactory.create(flightRequest) as T
                }
            }
    }
}