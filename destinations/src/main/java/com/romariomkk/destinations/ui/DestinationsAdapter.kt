package com.romariomkk.destinations.ui

import android.view.View
import androidx.databinding.ViewDataBinding
import com.romariomkk.core.ui.base.AbsRVAdapter
import com.romariomkk.core.ui.base.AbsRVViewHolder
import com.romariomkk.destinations.R
import com.romariomkk.destinations.databinding.ItemDestinationBinding
import com.romariomkk.destinations.domain.pojo.Destination

class DestinationsAdapter(
    private val onItemClick: (Destination) -> Unit
) : AbsRVAdapter<Destination, ViewDataBinding, AbsRVViewHolder<Destination, ViewDataBinding>>() {

    override fun getItemViewHolder(view: View, viewType: Int) =
        DestinationItemVH(view) { pos ->
            getItemAt(pos)?.let {
                onItemClick(it)
            }
        }

    override fun getItemLayout(position: Int) =
        R.layout.item_destination

    override fun onBindViewHolder(
        holder: AbsRVViewHolder<Destination, ViewDataBinding>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val prevItem = getItemAt(position - 1)
        val curItem = getItemAt(position)
        curItem?.let {
            prevItem?.let {
                if (prevItem.countryCode == curItem.countryCode)
                    super.onBindViewHolder(holder, position, mutableListOf(HIDE_COUNTRY_NAME))
                else
                    super.onBindViewHolder(holder, position)
            } ?: run {
                super.onBindViewHolder(holder, position)
            }
        }
    }

    override fun getVoidItem() = Destination.empty()
    override fun areItemsTheSame(oldItem: Destination, newItem: Destination) =
        oldItem.code == newItem.code
    override fun areContentTheSame(oldItem: Destination, newItem: Destination) = newItem == oldItem


    class DestinationItemVH(view: View, itemClickListener: (Int) -> Unit) :
        AbsRVViewHolder<Destination, ItemDestinationBinding>(view) {
        init {
            setOnClickListener(itemClickListener)
        }

        override fun bind(item: Destination, payloads: MutableList<Any>?) {
            binding?.let {
                it.destination = item
                it.isCountryHidden = payloads?.contains(HIDE_COUNTRY_NAME) ?: false
            }
        }
    }

    companion object {
        private const val HIDE_COUNTRY_NAME = "hide_country_name"
    }
}