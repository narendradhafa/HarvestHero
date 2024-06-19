package com.bangkitacademy.harvesthero.ui.add

import android.app.Application
import androidx.lifecycle.ViewModel
import com.bangkitacademy.harvesthero.data.PlantRepository
import com.bangkitacademy.harvesthero.data.local.entity.Plant

class AddViewModel(application: Application) : ViewModel() {

    private val mPlantRepository: PlantRepository = PlantRepository(application)

    fun insert(plant: Plant) {
        mPlantRepository.insert(plant)
    }
}