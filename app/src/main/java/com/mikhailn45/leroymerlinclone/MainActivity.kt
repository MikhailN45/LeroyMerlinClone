package com.mikhailn45.leroymerlinclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var specialSaleAdapter: RecyclerView.Adapter<RecyclerViewSpecialSaleAdapter.ViewHolder>? = null
    private var bestPriceAdapter:RecyclerView.Adapter<RecyclerViewBestPriceAdapter.ViewHolder>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewSpecialSale: RecyclerView = findViewById(R.id.recycler_view_special_sale)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewSpecialSale.layoutManager = layoutManager

        specialSaleAdapter = RecyclerViewSpecialSaleAdapter()
        recyclerViewSpecialSale.adapter = specialSaleAdapter

        val recyclerViewBestPrice: RecyclerView = findViewById(R.id.recycler_best_price)
        layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        recyclerViewBestPrice.layoutManager = layoutManager

        bestPriceAdapter = RecyclerViewBestPriceAdapter()
        recyclerViewBestPrice.adapter = bestPriceAdapter


    }
}