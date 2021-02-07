package com.romariomkk.search.ui.flights

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

object FlightBA {

    @JvmStatic
    @BindingAdapter("textFlightDate")
    fun TextView.setDate(date: Date?) {
        date?.let {
            val sdf = SimpleDateFormat("MMMM d yyyy", Locale.ENGLISH)
            text = sdf.format(date)
        }
    }
}