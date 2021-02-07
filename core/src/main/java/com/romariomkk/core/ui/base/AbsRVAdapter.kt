package com.romariomkk.core.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.romariomkk.core.R
import com.romariomkk.core.databinding.ItemEmptyBinding
import com.romariomkk.core.databinding.ItemLoadingBinding
import com.romariomkk.core.databinding.ItemLoadingErrorBinding
import com.romariomkk.core.util.DiffCallback
import java.util.*


abstract class AbsRVAdapter<T, out DB : ViewDataBinding, VH : AbsRVViewHolder<T, DB>>: RecyclerView.Adapter<VH>() {

    private val items = ArrayList<T>()

    private var isLoadingAdded = false

    protected abstract fun getItemLayout(position: Int): Int
    protected abstract fun getItemViewHolder(view: View, viewType: Int): VH
    protected abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean
    protected abstract fun areContentTheSame(oldItem: T, newItem: T): Boolean
    protected abstract fun getVoidItem(): T

    protected open fun getLoadingItemLayout() = R.layout.item_loading

    override fun getItemViewType(position: Int): Int {
        return if (position == items.lastIndex) {
            when {
                isLoadingAdded -> getLoadingItemLayout()
                else -> getItemLayout(position)
            }
        } else {
            getItemLayout(position)
        }
    }

    private fun getView(parent: ViewGroup, viewType: Int): View {
        val view: View
        if (viewType != -1) {
            view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        } else {
            throw IllegalStateException("No item layout specified for " + this.javaClass.name)
        }
        return view
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        getViewHolder(getView(parent, viewType), viewType) as VH

    private fun getViewHolder(
        view: View,
        viewType: Int
    ): AbsRVViewHolder<T, ViewDataBinding> {
        return when (viewType) {
            getLoadingItemLayout() ->
                LoadingItem(view)
            else ->
                getItemViewHolder(view, viewType)
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItemAt(position)?.let { holder.bind(it) }
    }

    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        getItemAt(position)?.let { holder.bind(it, payloads) }
    }


    /* All boilerplate methods */

    fun updateItems(newItems: List<T>) {
        val res = DiffUtil.calculateDiff(object : DiffCallback<T>(items, newItems) {

            override fun getNewListSize() = newItems.size

            override fun getOldListSize() = items.size

            override fun areItemsTheSame(oldItem: T, newItem: T) =
                this@AbsRVAdapter.areItemsTheSame(oldItem, newItem)

            override fun areContentsTheSame(oldItem: T, newItem: T) =
                this@AbsRVAdapter.areContentTheSame(oldItem, newItem)

        })

        removeAuxiliaryItems()

        items.clear()
        items.addAll(newItems)

        res.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = items.size

    fun getItemAt(position: Int): T? {
        return if (position < 0 || position >= items.size) null
        else items[position]
    }

    private fun add(item: T) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    private fun removeAt(index: Int) {
        items.removeAt(index)
        notifyItemRemoved(index)
    }


    fun addLoadingItem() {
        if (!isLoadingAdded) {
            isLoadingAdded = true
            add(getVoidItem())
        }
    }

    private fun removeLoadingItem() {
        if (isLoadingAdded) {
            isLoadingAdded = false
            removeAt(items.size - 1)
        }
    }

    fun removeAuxiliaryItems() {
        removeLoadingItem()
    }


    class LoadingItem<T>(view: View) : AbsRVViewHolder<T, ItemLoadingBinding>(view) {
        override fun bind(item: T, payloads: MutableList<Any>?) {}
    }
}
