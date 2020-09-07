package com.afterapps.wett.ui

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.afterapps.wett.R
import com.afterapps.wett.util.convertCelsiusToFahrenheit

//Change temps according to unit
@BindingAdapter("temp", "unit")
fun bindTempTextView(textView: TextView, temp: Int?, isUnitMetric: Boolean?) {
    temp?.let {
        val tempToDisplay = when (isUnitMetric) {
            true -> it
            else -> convertCelsiusToFahrenheit(it)
        }
        textView.text = tempToDisplay.toString()
    }
}


//Color the current active unit
@BindingAdapter("unitButton")
fun bindUnitTextColor(button: Button, isActive: Boolean?) {
    val text = button.context.getString(R.string.unit_button_text)
    val spannableString = SpannableString(text)
    when (isActive) {
        true -> spannableString.setSpan(
            ForegroundColorSpan(Color.BLACK),
            0,
            3,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        else -> spannableString.setSpan(
            ForegroundColorSpan(Color.BLACK),
            2,
            6,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    button.text = spannableString
}