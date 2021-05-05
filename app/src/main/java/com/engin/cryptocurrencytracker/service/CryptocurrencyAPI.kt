package com.engin.cryptocurrencytracker.service

import com.engin.cryptocurrencytracker.model.CryptoCurrencyModel
import com.engin.cryptocurrencytracker.util.ApiKey
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptocurrencyAPI {

    @GET("currencies/ticker?key=${ApiKey.apiKey}&")
    fun getCurrencies(@Query("ids") ids: String): Observable<List<CryptoCurrencyModel>>
}