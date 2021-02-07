package com.romariomkk.search.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.romariomkk.search.R
import com.romariomkk.search.databinding.FragmentFlightDetailBinding


class FlightDetailFragment : DialogFragment() {

    private val args by navArgs<FlightDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        (DataBindingUtil.inflate(inflater, R.layout.fragment_flight_detail, container, false)
                as FragmentFlightDetailBinding)
            .apply {
                flight = args.flight
            }.root
}