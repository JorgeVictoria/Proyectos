package com.jovian.p6_api_rest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jovian.p6_api_rest.R
import com.jovian.p6_api_rest.model.Amiibo

class AmiiboAdapter(val amiibos: List<Amiibo>):RecyclerView.Adapter<AmiiboViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmiiboViewHolder {
        val layoutInflate = LayoutInflater.from(parent.context)

        return AmiiboViewHolder(layoutInflate.inflate(R.layout.item_amiibo,parent, false))

    }

    override fun getItemCount(): Int = amiibos.size

    override fun onBindViewHolder(holder: AmiiboViewHolder, position: Int) {
        val item: Amiibo = amiibos[position]

        holder.bind(item)
    }

}