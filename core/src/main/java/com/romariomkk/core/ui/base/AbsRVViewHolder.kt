package com.romariomkk.core.ui.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class AbsRVViewHolder<T, out DB: ViewDataBinding>(
    containerView: View
) : RecyclerView.ViewHolder(containerView) {

    protected val binding: DB? = DataBindingUtil.bind(itemView)
    private var clickListener: ((Int) -> Unit)? = null

    abstract fun bind(item: T, payloads: MutableList<Any>? = null)

    init {
        itemView.setOnClickListener {
            clickListener?.invoke(adapterPosition)
        }
    }

    fun setOnClickListener(listener: (Int) -> Unit) {
        this.clickListener = listener
    }
}