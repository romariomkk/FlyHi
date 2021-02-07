package com.romariomkk.destinations.ui

import androidx.lifecycle.*
import com.romariomkk.core.util.Resource
import com.romariomkk.destinations.domain.pojo.Destination
import com.romariomkk.destinations.domain.usecase.contract.SearchDestinationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DestinationListingVM @Inject constructor(
    private val searchDestinationsUseCase: SearchDestinationsUseCase
): ViewModel() {

    val query = MutableLiveData<String>("")
    val destinations = query.switchMap {
        liveData {
            emit(Resource.loading())
            emit(searchDestinationsUseCase.searchDestinations(it))
        }
    }

    fun search() {
        query.value = query.value
    }
}