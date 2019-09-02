package com.lucas.izidorio.conversor.model.retrofit.provider

import com.lucas.izidorio.conversor.model.retrofit.serialization.Conversion
import com.lucas.izidorio.conversor.model.retrofit.service.ApiService
import io.reactivex.Observable

class ServiceProvider(private val apiService: ApiService) {
    fun convert(from: String, to: String): Observable<Conversion> {
        return apiService.getConversion(base = from, symbols = to)
    }
}