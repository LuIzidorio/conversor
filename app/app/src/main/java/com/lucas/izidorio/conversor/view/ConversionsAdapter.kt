package com.lucas.izidorio.conversor.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucas.izidorio.conversor.R
import com.lucas.izidorio.conversor.model.retrofit.serialization.Conversion
import kotlinx.android.synthetic.main.item_conversion.view.*

class ConversionsAdapter(private val conversions: List<com.lucas.izidorio.conversor.model.roomdb.entities.Conversion>,
                         private val context: Context
) : RecyclerView.Adapter<ConversionsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val conversion = conversions[position]
        holder.bindView(conversion.format(), context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_conversion, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return conversions.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindView(item: Conversion, context: Context) {
            val base = itemView.base
            val rate = itemView.rate
            val rateValue = itemView.rateValue
            val date = itemView.date
            val conversionValues = itemView.conversionValues

            val currency = item.rates.getRateValue(context)

            base.text = item.base
            rate.text = currency?.name
            rateValue.text = "1.0 ${item.base} = ${currency?.value} ${currency?.name}"
            date.text = "(${item.date})"
            conversionValues.text = "${item.rates.oldCurrencyValue} ${item.base} → ${item.rates.newCurrencyValue} ${currency?.name}"
        }

    }

}
