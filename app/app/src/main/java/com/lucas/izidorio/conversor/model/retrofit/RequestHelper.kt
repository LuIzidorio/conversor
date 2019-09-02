package com.lucas.izidorio.conversor.model.retrofit

import android.util.Log
import com.lucas.izidorio.conversor.model.retrofit.provider.ServiceProvider
import com.lucas.izidorio.conversor.model.retrofit.serialization.Conversion
import com.lucas.izidorio.conversor.model.retrofit.service.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RequestHelper {
    fun convert(from: String, to: String, doing: (result: Conversion) -> Unit) {
        Thread {
            val provider = ServiceProvider(ApiService.create())
            provider.convert(from, to)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    doing(result)
                }, { error ->
                    Log.e("RequestHelper/convert", "Error: $error")
                })
        }.start()
    }
}