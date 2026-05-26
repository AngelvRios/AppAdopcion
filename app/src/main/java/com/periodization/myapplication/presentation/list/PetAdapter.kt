package com.periodization.myapplication.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.periodization.myapplication.R
import com.periodization.myapplication.databinding.ItemMascotaBinding
import com.periodization.myapplication.domain.model.Pet

class PetAdapter(
    private val onPetClick: (Pet) -> Unit
) : ListAdapter<Pet, PetAdapter.PetViewHolder>(PetDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val binding = ItemMascotaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.bind(getItem(position), onPetClick)
    }

    class PetViewHolder(private val binding: ItemMascotaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pet: Pet, onPetClick: (Pet) -> Unit) {
            binding.tvNombre.text = pet.name
            binding.tvUbicacion.text = binding.root.context.getString(R.string.ubicacion_label, pet.location ?: "N/A")
            binding.tvEdad.text = binding.root.context.getString(R.string.edad_label, pet.age)
            
            binding.ivMascota.load(pet.imageUrl) {
                crossfade(true)
                placeholder(android.R.drawable.ic_menu_gallery)
                error(android.R.drawable.ic_menu_report_image)
            }

            binding.root.setOnClickListener { onPetClick(pet) }
        }
    }

    class PetDiffCallback : DiffUtil.ItemCallback<Pet>() {
        override fun areItemsTheSame(oldItem: Pet, newItem: Pet): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pet, newItem: Pet): Boolean {
            return oldItem == newItem
        }
    }
}