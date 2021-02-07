package com.romariomkk.destinations.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.romariomkk.core.ui.base.BaseFragment
import com.romariomkk.core.util.Resource
import com.romariomkk.destinations.R
import com.romariomkk.destinations.databinding.FragmentDestinationListingBinding
import com.romariomkk.destinations.domain.pojo.Destination
import com.romariomkk.destinations.util.Keys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DestinationListingFragment :
    BaseFragment<FragmentDestinationListingBinding, DestinationListingVM>() {

    override val layoutRes =
        R.layout.fragment_destination_listing
    override val vmClass =
        DestinationListingVM::class.java


    private val direction by navArgs<DestinationListingFragmentArgs>()

    private lateinit var destinationsAdapter: DestinationsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        destinationsAdapter = DestinationsAdapter {
            parentFragmentManager.setFragmentResult(Keys.KEY_DESTINATION_RESULT, bundleOf(
                Keys.KEY_FROM_OR_TO to direction.fromOrTo,
                Keys.KEY_DESTINATION to it
            ))
            navController.popBackStack()
        }

        with(binding) {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

            ivClose.setOnClickListener { navController.popBackStack() }
            tvError.setOnClickListener { viewModel.search() }

            with (rvDestinations) {
                adapter = destinationsAdapter
                setEmptyView(tvEmpty)
                setErrorView(tvError)
            }
        }

        viewModel.destinations.observe(viewLifecycleOwner, destinationsObserver)
    }

    private val destinationsObserver = Observer<Resource<List<Destination>>> {
        when (it.status) {
            Resource.Status.Loading -> {
                destinationsAdapter.addLoadingItem()
            }
            Resource.Status.Success -> {
                it.data?.let { list ->
                    destinationsAdapter.updateItems(list)
                }
            }
            Resource.Status.Error -> {
                Keys.ERROR_MAP[it?.exception?.message]?.let { errorRes -> binding.tvError.setText(errorRes) }
                binding.rvDestinations.showError()
            }
        }
    }
}