package com.bangkitacademy.harvesthero.ui.plantpedia

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkitacademy.harvesthero.data.local.entity.Plant
import com.bangkitacademy.harvesthero.databinding.ItemListPediaBinding

class PlantPediaAdapter(private val onItemClick: (Plant) -> Unit) : ListAdapter<Plant, PlantPediaAdapter.PlantPediaViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantPediaViewHolder {
        val binding = ItemListPediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlantPediaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantPediaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PlantPediaViewHolder(private val binding: ItemListPediaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(plant: Plant) {
            binding.apply {
                tvTypeItemPedia.text = plant.plantType

                root.setOnClickListener {
                    onItemClick.invoke(plant)
                }
            }
            Log.d("PlantPediaAdapter", "Binding plant: ${plant.plantType}")
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Plant>() {
            override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
                return oldItem == newItem
            }
        }
    }

}