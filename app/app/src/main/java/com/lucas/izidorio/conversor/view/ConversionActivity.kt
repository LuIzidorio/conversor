package com.lucas.izidorio.conversor.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.lucas.izidorio.conversor.R
import com.lucas.izidorio.conversor.presenter.ConversionActivityPresenter
import kotlinx.android.synthetic.main.activity_conversion.*

class ConversionActivity : AppCompatActivity(), ConversionActivityPresenter.View {

    private var presenter: ConversionActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversion)

        presenter = ConversionActivityPresenter(this)

        presenter?.initAdapter(this)

        oldCurrencySelector.setOnClickListener {
            presenter?.hideKeyboard(this, rootView)
            presenter?.selectCurrency(this, oldCurrency)
        }

        newCurrencySelector.setOnClickListener {
            presenter?.hideKeyboard(this, rootView)
            presenter?.selectCurrency(this, newCurrency)
        }

        convertButton.setOnClickListener {
            presenter?.hideKeyboard(this, rootView)
            presenter?.convert()
        }
    }

    override fun getOldCurrency(): String {
        return oldCurrency.text.toString()
    }

    override fun getNewCurrency(): String {
        return newCurrency.text.toString()
    }

    override fun getOldCurrencyValue(): String {
        return oldCurrencyInput.text.toString()
    }

    override fun setNewCurrencyValue(newValue: String) {
        newCurrencyInput.setText(newValue)
    }
}
