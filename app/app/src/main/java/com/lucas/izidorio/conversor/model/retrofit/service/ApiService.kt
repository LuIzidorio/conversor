package com.lucas.izidorio.conversor.model.retrofit.service

import com.lucas.izidorio.conversor.model.retrofit.serialization.Conversion
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("latest")
    fun getConversion(@Query("base") base: String,
                      @Query("symbols") symbols: String): Observable<Conversion>

    companion object Factory {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.exchangeratesapi.io/")
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}