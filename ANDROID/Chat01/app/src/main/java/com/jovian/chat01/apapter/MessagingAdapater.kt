package com.jovian.chat01.apapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jovian.chat01.R
import com.jovian.chat01.model.Message
import com.jovian.chat01.utils.Constants.RECEIVE_ID
import com.jovian.chat01.utils.Constants.SEND_ID

class MessagingAdapater: RecyclerView.Adapter<MessagingAdapater.MessageViewHolder>() {

    var messagesList = mutableListOf<Message>()

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tv_send : TextView = itemView.findViewById(R.id.tv_send)
        val tv_receive : TextView = itemView.findViewById(R.id.tv_receive)

        init {
            itemView.setOnClickListener {

                //Remove message on the item clicked
                messagesList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messagesList[position]

        when (currentMessage.id) {
            SEND_ID -> {
                holder.tv_send.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.tv_receive.visibility = View.GONE
            }
            RECEIVE_ID -> {
                holder.tv_receive.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.tv_send.visibility = View.GONE
            }
        }
    }

    fun insertMessage(message: Message) {
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
    }

}