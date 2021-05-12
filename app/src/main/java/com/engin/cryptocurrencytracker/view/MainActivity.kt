package com.engin.cryptocurrencytracker.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.engin.cryptocurrencytracker.R
import com.engin.cryptocurrencytracker.adapter.CurrencyAdapter
import com.engin.cryptocurrencytracker.model.CryptoCurrencyModel
import com.engin.cryptocurrencytracker.service.CryptocurrencyAPI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val BASE_URL = "https://api.nomics.com/v1/"
    private lateinit var compositeDisposable: CompositeDisposable
    private var currencyList: List<CryptoCurrencyModel> = arrayListOf()
    private lateinit var selectedCurrencies: String
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.currencyRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = CurrencyAdapter(currencyList)
        compositeDisposable = CompositeDisposable()
        selectedCurrencies = "BTC"
        getData()
    }

    private fun getData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build().create(CryptocurrencyAPI::class.java)

        compositeDisposable.add(
            retrofit.getCurrencies(selectedCurrencies)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )
    }

    private fun handleResponse(currencies: List<CryptoCurrencyModel>) {
        currencyList = ArrayList(currencies)
        currencyList.let {
            for (currency in currencyList) {
                println(currency.logoUrl)
            }
        }
        recyclerView.adapter = CurrencyAdapter(currencyList)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun selectCurrencies(selectedCoins: List<String>) {
        val coins = selectedCoins.joinToString(",")
        if (selectedCurrencies == "") {
            selectedCurrencies = coins
        } else {
            selectedCurrencies += ",$coins"
        }
        getData()
    }
}