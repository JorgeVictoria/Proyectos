package com.jovian.p6_api_rest.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jovian.p6_api_rest.databinding.ItemAmiiboBinding
import com.jovian.p6_api_rest.model.Amiibo
import com.squareup.picasso.Picasso

class AmiiboViewHolder(view: View): RecyclerView.ViewHolder(view) {

    //añadimos el binding
    private val binding = ItemAmiiboBinding.bind(view)

    //elementos que mostraremos en cada elemento de la lista
    fun bind(image: Amiibo){
        Picasso.get().load(image.image).into(binding.ivAmiibo)
        binding.tvName.text = "Name: " + image.name
        binding.tvSeries.text = "Game Series: " + image.gameSeries
    }
}


