package com.jovian.chat02.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jovian.chat02.R
import com.jovian.chat02.model.Message
import com.jovian.chat02.utils.Constants.RECEIVE_ID
import com.jovian.chat02.utils.Constants.SEND_ID

/**
 * clase para construir un RecyclerView con los mensajes enviados y recibidos
 */
class MessagingAdapter : RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {

    //listado con todos los mensajes
    var messagesList = mutableListOf<Message>()

    /**
     * en este viewholder asignamos una variable para cada uno de los componentes de recyclerView
     * Basicamente en el recyclerview tenemos 2 textView que estan alineados a izda y derecha
     * en funcion de si el mensaje es enviado o recibido
     */
    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tv_send: TextView = itemView.findViewById(R.id.tv_send)
        val tv_receive: TextView = itemView.findViewById(R.id.tv_receive)

        //por si queremos eliminar los mensajes con un click. En principio lo desactivo
        /**init {
        itemView.setOnClickListener {

        //Remove message on the item clicked
        messagesList.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
        }
        }**/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        )
    }

    /**
     * devuelve el total de mensjajes
     */
    override fun getItemCount(): Int {
        return messagesList.size
    }

    /**
     * Rellenamos los diferentes items del RecyclerView con la fecha de envio/recepcion y el mensaje
     */
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messagesList[position]

        when (currentMessage.id) {
            SEND_ID -> {
                holder.tv_send.apply {
                    text = currentMessage.time + "\n" + currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.tv_receive.visibility = View.GONE
            }
            RECEIVE_ID -> {
                holder.tv_receive.apply {
                    text = currentMessage.time + "\n" + currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.tv_send.visibility = View.GONE
            }
        }
    }

    /**
     * funcion para insertar los mensajes en la lista
     */
    fun insertMessage(message: Message) {
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
    }

}