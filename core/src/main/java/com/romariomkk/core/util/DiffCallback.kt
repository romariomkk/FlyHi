package com.romariomkk.core.util

import androidx.recyclerview.widget.DiffUtil

abstract class DiffCallback<T>(
    private val oldList: List<T>,
    private val newList: List<T>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList.getOrNull(oldItemPosition) ?: return false
        val newItem = newList.getOrNull(newItemPosition) ?: return false
        return areItemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList.getOrNull(oldItemPosition) ?: return false
        val newItem = newList.getOrNull(newItemPosition) ?: return false
        return areContentsTheSame(oldItem, newItem)
    }

    abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean

    abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean

}
