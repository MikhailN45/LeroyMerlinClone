package com.mikhailn45.leroymerlinclone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewSpecialSaleAdapter : RecyclerView.Adapter<RecyclerViewSpecialSaleAdapter.ViewHolder>() {

    private val itemPrices =
        arrayOf("68 ₽/шт.", "143 ₽/шт.", "807 ₽/шт.", "316 ₽/шт.", "1210 ₽/шт.", "3691 ₽/шт.")
    private val itemDetails = arrayOf(
        "Очиститель для стёкол Clean Glass 600 мл ",
        "Пескобетон Axton 30 кг",
        "Лопата штыковая Fiskars SolidTM 116 см сталь с черенком ",
        "Подушка «Бархат», 40х40 см, цвет жёлтый",
        "Покрывало, 160х200 см, полиэстер, цвет марсала",
        "Тачка садовая двухколесная усиленная 320 кг/100 л"
    )
    private val itemImages = intArrayOf(
        R.drawable.glass_cleaner,
        R.drawable.beton,
        R.drawable.lopata,
        R.drawable.podushka,
        R.drawable.odeyalo,
        R.drawable.telejka
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.item_image)
        var price: TextView = itemView.findViewById(R.id.item_title)
        var description: TextView = itemView.findViewById(R.id.item_subtitle)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.price.text = itemPrices[position]
        holder.description.text = itemDetails[position]
        holder.image.setImageResource(itemImages[position])
    }

    override fun getItemCount(): Int {
        return itemPrices.size
    }

}