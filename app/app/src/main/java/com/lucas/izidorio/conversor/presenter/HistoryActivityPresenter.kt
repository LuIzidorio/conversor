package com.lucas.izidorio.conversor.presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.lucas.izidorio.conversor.model.roomdb.AppDatabase
import com.lucas.izidorio.conversor.view.ConversionActivity
import com.lucas.izidorio.conversor.view.ConversionsAdapter

class HistoryActivityPresenter(private val view: View) {

    private val tag = "HistoryActivityPresenter"
    private var adapter: ConversionsAdapter? = null

    fun load(source: Activity) {
        Thread {
            adapter = ConversionsAdapter(AppDatabase(source).conversionDao().getAll(), source)
            source.runOnUiThread {
                view.initAdapter(adapter!!)
            }
        }.start()
    }

    interface View {
        fun initAdapter(adapter: ConversionsAdapter)
    }
}