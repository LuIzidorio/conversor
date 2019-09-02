package com.lucas.izidorio.conversor.presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import com.google.gson.Gson
import com.lucas.izidorio.conversor.R
import com.lucas.izidorio.conversor.model.retrofit.serialization.Conversion
import com.lucas.izidorio.conversor.model.retrofit.RequestHelper
import com.lucas.izidorio.conversor.model.roomdb.AppDatabase
import com.lucas.izidorio.conversor.view.HistoryActivity
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

    fun convert(context: Context) {
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
                view.setNewCurrencyValue(df.format(conversion))

                Thread {
                    val newConversion = com.lucas.izidorio.conversor.model.roomdb.entities.Conversion()
                    newConversion.info = Gson().toJson(result)
                    AppDatabase(context).conversionDao().insert(newConversion)
                }.start()
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

    fun showHistory(source: Activity) {
        val intent = Intent(source, HistoryActivity::class.java)
        view.startNextActivity(intent)
    }

    interface View {
        fun getOldCurrency(): String
        fun getNewCurrency(): String
        fun getOldCurrencyValue(): String
        fun setNewCurrencyValue(newValue: String)
        fun startNextActivity(i: Intent)
    }
}