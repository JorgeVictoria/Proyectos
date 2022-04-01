package com.jovian.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

//recibe como entrada una lista de personas, creada anteriormente
class PersonAdapter (val persona:List<Persona>):RecyclerView.Adapter<PersonAdapter.PersonHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return PersonHolder(layoutInflater.inflate(R.layout.item_persona,parent,false))

    }

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {

        holder.render(persona[position])
    }

    //devolvemos el tamaño de la lista que hemos recibido
    override fun getItemCount(): Int {
       return persona.size
    }

    class PersonHolder(private val view: View):RecyclerView.ViewHolder(view){

        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val tvApellido: TextView = view.findViewById(R.id.tvApellido)
        val tvTelefono: TextView = view.findViewById(R.id.tvTelefono)
        fun render(persona: Persona){
            tvNombre.text = persona.nombre
            tvApellido.text = persona.surname
            tvTelefono.text = persona.telefono
        }

    }
}