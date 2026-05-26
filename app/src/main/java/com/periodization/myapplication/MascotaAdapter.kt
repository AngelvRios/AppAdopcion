package com.periodization.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class MascotaAdapter(
    private var mascotas: List<Mascota>,
    private val onItemClick: (Mascota) -> Unit
) : RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder>() {

    class MascotaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivMascota: ImageView = view.findViewById(R.id.ivMascota)
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val tvUbicacion: TextView = view.findViewById(R.id.tvUbicacion)
        val tvEdad: TextView = view.findViewById(R.id.tvEdad)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mascota, parent, false)
        return MascotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MascotaViewHolder, position: Int) {
        val mascota = mascotas[position]
        val context = holder.itemView.context
        
        holder.tvNombre.text = mascota.nombre
        holder.tvUbicacion.text = context.getString(R.string.ubicacion_label, mascota.ubicacion)
        holder.tvEdad.text = context.getString(R.string.edad_label, mascota.edad)
        
        // Cargar imagen con Coil
        holder.ivMascota.load(mascota.imageUrl) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_gallery)
            error(android.R.drawable.ic_menu_report_image)
        }
        
        holder.itemView.setOnClickListener { onItemClick(mascota) }
    }

    override fun getItemCount() = mascotas.size

    fun updateData(nuevasMascotas: List<Mascota>) {
        mascotas = nuevasMascotas
        notifyDataSetChanged()
    }
}