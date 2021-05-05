package com.engin.cryptocurrencytracker.model

import com.google.gson.annotations.SerializedName

data class CryptoCurrencyModel(
    @SerializedName("currency")
    var currency: String,
    @SerializedName("price")
    var price: Double,
    @SerializedName("price_date")
    var priceDate: String,
    @SerializedName("logo_url")
    var logoUrl: String,
)
