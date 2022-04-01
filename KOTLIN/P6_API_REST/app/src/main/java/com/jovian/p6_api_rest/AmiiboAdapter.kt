package com.jovian.p6_api_rest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AmiiboAdapter(val images: List<Amiibo>):RecyclerView.Adapter<AmiiboViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmiiboViewHolder {
        val layoutInflate = LayoutInflater.from(parent.context)

        return AmiiboViewHolder(layoutInflate.inflate(R.layout.item_amiibo,parent, false))

    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: AmiiboViewHolder, position: Int) {
        val item: Amiibo = images[position]

        holder.bind(item)
    }

}