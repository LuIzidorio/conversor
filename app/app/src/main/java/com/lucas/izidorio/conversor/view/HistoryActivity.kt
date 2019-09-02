package com.lucas.izidorio.conversor.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucas.izidorio.conversor.R
import com.lucas.izidorio.conversor.presenter.HistoryActivityPresenter
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity(), HistoryActivityPresenter.View {

    private var presenter: HistoryActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        presenter = HistoryActivityPresenter(this)
        presenter?.load(this)
    }

    override fun initAdapter(adapter: ConversionsAdapter) {
        conversionsHistory.adapter = adapter
    }
}
