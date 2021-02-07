package com.romariomkk.flyhigh.ui.main

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.Observer
import com.romariomkk.core.ui.base.BaseFragment
import com.romariomkk.destinations.domain.pojo.Destination
import com.romariomkk.destinations.ui.DestinationDirection
import com.romariomkk.destinations.util.Keys
import com.romariomkk.flyhigh.R
import com.romariomkk.flyhigh.databinding.FragmentStartBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class StartFragment : BaseFragment<FragmentStartBinding, StartVM>() {

    override val layoutRes =
        R.layout.fragment_start
    override val vmClass =
        StartVM::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener(Keys.KEY_DESTINATION_RESULT, viewLifecycleOwner, destinationResultListener)
        with(binding) {
            arrayOf(npTeens, npChildren).forEach { it.minValue = 0 }
            with (npAdults) {
                minValue = 1
                maxValue = 10
            }
            npTeens.maxValue = 10

            dsvFrom.setOnClickListener {
                navController.navigate(StartFragmentDirections.toDestinationSelect(DestinationDirection.FROM))
            }
            dsvTo.setOnClickListener {
                navController.navigate(StartFragmentDirections.toDestinationSelect(DestinationDirection.TO))
            }
            tvDate.setOnClickListener {
                val cal = Calendar.getInstance()
                val dialog = DatePickerDialog(
                    requireContext(), datePickDialogListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).apply {
                    datePicker.minDate = Date().time
                }
                dialog.show()
            }

            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    private val destinationResultListener =
        FragmentResultListener { requestKey, result ->
            if (requestKey == Keys.KEY_DESTINATION_RESULT) {
                viewModel.setDestination(
                    result[Keys.KEY_FROM_OR_TO] as DestinationDirection,
                    result[Keys.KEY_DESTINATION] as Destination)
            }
        }

    override fun onStart() {
        super.onStart()
        with (viewModel) {
            adults.observe(viewLifecycleOwner, Observer {
                binding.npChildren.maxValue = it
            })
            flightRequest.observe(viewLifecycleOwner, Observer {
                navController.navigate(StartFragmentDirections.toFlightSearch(it))
            })
        }
    }

    private val datePickDialogListener =
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            viewModel.date.value = Calendar.getInstance().apply {
                set(year, month, dayOfMonth, 0, 0, 0)
            }.time
        }

}