package com.romariomkk.flyhigh.ui.main

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.romariomkk.destinations.domain.pojo.Destination
import com.romariomkk.destinations.ui.widget.DestinationSelectView
import java.text.SimpleDateFormat
import java.util.*

object StartBA {

    @JvmStatic
    @BindingAdapter("destination")
    fun DestinationSelectView.setDest(destination: Destination?) {
        destination?.let {
            setDestination(destination.name, destination.code)
        }
    }

    @JvmStatic
    @BindingAdapter("textDate")
    fun TextView.setDateText(date: Date?) {
        date?.let {
            val fmtOut = SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH)
            text = fmtOut.format(date)
        }
    }
}