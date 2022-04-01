package com.jovian.repte.model

import com.google.gson.annotations.SerializedName

data class Pedido(

    val idPedido: Long,
    val productos: List<Producto>

    )