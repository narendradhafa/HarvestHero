package com.bangkitacademy.harvesthero.ui.myplants

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkitacademy.harvesthero.data.local.entity.Plant
import com.bangkitacademy.harvesthero.databinding.ItemListPlantBinding

class MyPlantsAdapter(private val onItemClick: (Plant) -> Unit) : ListAdapter<Plant, MyPlantsAdapter.MyPlantsViewHolder>(DIFF_CALLBACK) {

    private val listPlant = ArrayList<Plant>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPlantsViewHolder {
        val binding = ItemListPlantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPlantsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPlantsViewHolder, position: Int) {
        holder.bind(listPlant[position])
    }

    override fun getItemCount(): Int {
        return listPlant.size
    }

    inner class MyPlantsViewHolder(private val binding: ItemListPlantBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(plant: Plant) {
            binding.apply {
                tvNameItemMyplants.text = plant.plantName
                tvTypeItemMyplants.text = plant.plantType
                tvGrowthItemMyplants.text = plant.currentGrowth

                root.setOnClickListener {
                    onItemClick.invoke(plant)
                }
            }
            Log.d("MyPlantsAdapter", "Binding plant: ${plant.plantName}")
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