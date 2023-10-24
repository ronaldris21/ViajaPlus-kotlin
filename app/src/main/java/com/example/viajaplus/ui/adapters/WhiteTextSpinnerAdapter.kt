package com.example.viajaplus.ui.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class WhiteTextSpinnerAdapter(
    context: Context,
    resource: Int,
    items: List<String>
) : ArrayAdapter<String>(context, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        (view as TextView).setTextColor(ContextCompat.getColor(context, android.R.color.white))
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        (view as TextView).setTextColor(ContextCompat.getColor(context, android.R.color.black))
        return view
    }
}
