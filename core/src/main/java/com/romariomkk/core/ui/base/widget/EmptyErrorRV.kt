package com.romariomkk.core.ui.base.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

class EmptyErrorRV @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attributeSet, defStyleAttr) {

    private var emptyView: View? = null
    private var errorView: View? = null
    private var isError = false

    fun setEmptyView(emptyView: View) {
        this.emptyView = emptyView
    }

    fun setErrorView(errorView: View) {
        this.errorView = errorView
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        getAdapter()?.unregisterAdapterDataObserver(dataObserver)

        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(dataObserver)
        updateViews()
    }

    private val dataObserver = DefaultAdapterDataObserver(::hideErrorView)

    fun showError() {
        isError = true
        updateViews()
    }

    fun hideErrorView() {
        isError = false
        updateViews()
    }

    private fun updateViews() {
        updateEmptyView()
        updateErrorView()
    }

    private fun updateEmptyView() {
        emptyView?.let {
            val isEmpty = adapter?.itemCount == 0
            it.isVisible = isEmpty && !shouldShowErrorView()
            isVisible = !isEmpty && !shouldShowErrorView()
        }
    }

    private fun updateErrorView() {
        errorView?.isVisible = shouldShowErrorView()
    }

    private fun shouldShowErrorView() =
        errorView != null && isError

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        adapter?.unregisterAdapterDataObserver(dataObserver)
        emptyView = null
        errorView = null
    }

    private class DefaultAdapterDataObserver(private val doOnAny: () -> Unit) : AdapterDataObserver() {
        override fun onChanged() {
            doOnAny()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            doOnAny()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            doOnAny()
        }
    }
}