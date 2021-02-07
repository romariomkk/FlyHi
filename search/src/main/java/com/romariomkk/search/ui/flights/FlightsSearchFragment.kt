package com.romariomkk.search.ui.flights

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.romariomkk.core.ui.base.BaseFragment
import com.romariomkk.core.util.Resource
import com.romariomkk.search.R
import com.romariomkk.search.databinding.FragmentFlightsSearchBinding
import com.romariomkk.search.domain.pojo.Flight
import com.romariomkk.search.util.Keys
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FlightsSearchFragment : BaseFragment<FragmentFlightsSearchBinding, FlightsSearchVM>() {

    override val layoutRes =
        R.layout.fragment_flights_search
    override val vmClass =
        FlightsSearchVM::class.java

    @Inject
    lateinit var vmFactory: FlightsSearchVM.Factory

    private val args by navArgs<FlightsSearchFragmentArgs>()
    override val viewModel: FlightsSearchVM by viewModels(
        factoryProducer = { FlightsSearchVM.provideFactory(vmFactory, args.flightRequest) }
    )


    private lateinit var flightAdapter: FlightsSearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            flightRequest = args.flightRequest

            flightAdapter = FlightsSearchAdapter { flight ->
                navController.navigate(FlightsSearchFragmentDirections.toFlightDetail(flight))
            }

            tvEmpty.setOnClickListener { navController.popBackStack() }
            tvError.setOnClickListener { viewModel.searchFlights() }
            with (rvFlights) {
                adapter = flightAdapter
                setEmptyView(tvEmpty)
                setErrorView(tvError)
            }

            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        viewModel.flights.observe(viewLifecycleOwner, flightsObserver)
    }

    private val flightsObserver = Observer<Resource<List<Flight>>> {
        binding.clPrice.isInvisible = true
        when (it.status) {
            Resource.Status.Loading -> {
                flightAdapter.addLoadingItem()
            }
            Resource.Status.Success -> {
                it.data?.let { list ->
                    flightAdapter.updateItems(list)
                    binding.clPrice.isVisible = true
                }
            }
            Resource.Status.Error -> {
                Keys.ERROR_MAP[it.exception?.message]?.let { strRes -> binding.tvError.setText(strRes) }
                binding.rvFlights.showError()
            }
        }
    }

}