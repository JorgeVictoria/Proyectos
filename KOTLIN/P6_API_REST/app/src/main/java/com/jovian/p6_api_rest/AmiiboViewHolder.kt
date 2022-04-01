package com.jovian.p6_api_rest

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jovian.p6_api_rest.databinding.ItemAmiiboBinding
import com.squareup.picasso.Picasso

class AmiiboViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemAmiiboBinding.bind(view)


    fun bind(image: Amiibo){
        Picasso.get().load(image.image).into(binding.ivAmiibo)
        binding.tvName.text = "Name: " + image.name
        binding.tvSeries.text = "Game Series: " + image.gameSeries
    }
}


