package com.romariomkk.search.ui.flights

import android.view.View
import androidx.databinding.ViewDataBinding
import com.romariomkk.core.ui.base.AbsRVAdapter
import com.romariomkk.core.ui.base.AbsRVViewHolder
import com.romariomkk.search.R
import com.romariomkk.search.databinding.ItemFlightBinding
import com.romariomkk.search.domain.pojo.Flight

class FlightsSearchAdapter(private val onItemClick: (Flight) -> Unit)
    : AbsRVAdapter<Flight, ViewDataBinding, AbsRVViewHolder<Flight, ViewDataBinding>>() {

    override fun getItemLayout(position: Int) =
        R.layout.item_flight

    override fun getItemViewHolder(view: View, viewType: Int) =
        FlightVH(view) { pos ->
            getItemAt(pos)?.let { onItemClick(it) }
        }

    override fun areItemsTheSame(oldItem: Flight, newItem: Flight) = oldItem == newItem
    override fun areContentTheSame(oldItem: Flight, newItem: Flight) = oldItem == newItem
    override fun getVoidItem() = Flight()

    class FlightVH(view: View, itemClickListener: (Int) -> Unit): AbsRVViewHolder<Flight, ItemFlightBinding>(view) {
        init {
            setOnClickListener(itemClickListener)
        }

        override fun bind(item: Flight, payloads: MutableList<Any>?) {
            binding?.flight = item
        }
    }
}