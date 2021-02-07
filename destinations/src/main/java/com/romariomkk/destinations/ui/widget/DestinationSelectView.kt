package com.romariomkk.destinations.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.romariomkk.destinations.R

class DestinationSelectView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attributeSet, defStyleAttr) {

    init {
        ViewGroup.inflate(context, R.layout.view_destination_select, this)
        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.DestinationSelectView)
        val icon = attrs.getDrawable(R.styleable.DestinationSelectView_icon)
        val hint = attrs.getString(R.styleable.DestinationSelectView_hint)
        attrs.recycle()

        findViewById<ImageView>(R.id.ivIcon).setImageDrawable(icon)
        findViewById<TextView>(R.id.tvHint).text = hint
    }

    fun setDestination(name: String, code: String) {
        with(findViewById<TextView>(R.id.tvSelectedName)) {
            text = name
            isVisible = true
        }
        with(findViewById<TextView>(R.id.tvAirportCode)) {
            text = code
            isVisible = true
        }
    }
}