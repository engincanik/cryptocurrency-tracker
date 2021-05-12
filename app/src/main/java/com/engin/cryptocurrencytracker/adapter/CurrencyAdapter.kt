package com.engin.cryptocurrencytracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.engin.cryptocurrencytracker.R
import com.engin.cryptocurrencytracker.model.CryptoCurrencyModel

class CurrencyAdapter(private  var currencies: List<CryptoCurrencyModel>) : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coinName: TextView = itemView.findViewById(R.id.coinNameTv)
        val coinPrice: TextView = itemView.findViewById(R.id.coinPriceTv)
        val coinIcon: ImageView = itemView.findViewById(R.id.coinIconIv)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.currency_card, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CurrencyAdapter.ViewHolder, position: Int) {
        holder.coinName.text = currencies[position].currency
        holder.coinPrice.text = "Price: ${currencies[position].price}"
    }

    override fun getItemCount(): Int {
        return currencies.size
    }
}