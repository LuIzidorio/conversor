package com.lucas.izidorio.conversor.presenter

import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import com.google.gson.Gson
import com.lucas.izidorio.conversor.R
import com.lucas.izidorio.conversor.model.retrofit.Conversion
import com.lucas.izidorio.conversor.model.retrofit.RequestHelper
import kotlinx.android.synthetic.main.activity_conversion.*
import org.michaelbel.bottomsheet.BottomSheet
import java.lang.Exception
import java.math.RoundingMode
import java.text.DecimalFormat

class ConversionActivityPresenter(private val view: View) {

    private val tag = "ConversionActivityPresenter"
    private lateinit var currencyAdapter: ArrayAdapter<CharSequence>

    fun initAdapter(context: Context) {
        currencyAdapter = ArrayAdapter.createFromResource(context, R.array.currency_list, android.R.layout.simple_spinner_item)
    }

    fun convert() {
        val oldCurrency = view.getOldCurrency()
        val newCurrency = view.getNewCurrency()
        val value: Double = try {
            view.getOldCurrencyValue().toDouble()
        } catch (e: Exception) {
            0.0
        }
        RequestHelper.convert(oldCurrency, newCurrency) { result ->
            if (result.rates.getCurrencyValue(newCurrency) == null) {
                view.setNewCurrencyValue("Erro ao converter")
            } else {
                val conversion = value * result.rates.getCurrencyValue(newCurrency)!!
                result.rates.oldCurrencyValue = value
                result.rates.newCurrencyValue = conversion
                val df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.CEILING
                val jsonString = Gson().toJson(result)
                Log.e("Teste", "Value: $jsonString | ${Gson().fromJson(jsonString, Conversion::class.java)}")
                view.setNewCurrencyValue(df.format(conversion))
            }
        }
    }

    fun selectCurrency(context: Context, currencyView: TextView) {
        BottomSheet.Builder(context)
            .setTitle("Moeda")
            .setItems(R.array.currency_list) { _, item ->
                currencyView.text = currencyAdapter.getItem(item)
            }.show()
    }

    fun hideKeyboard(context: Context, rootView: android.view.View) {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(rootView.windowToken, 0)
    }

    interface View {
        fun getOldCurrency(): String
        fun getNewCurrency(): String
        fun getOldCurrencyValue(): String
        fun setNewCurrencyValue(newValue: String)
    }
}