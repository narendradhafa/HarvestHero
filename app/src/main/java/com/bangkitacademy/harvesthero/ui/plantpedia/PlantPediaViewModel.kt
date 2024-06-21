package com.bangkitacademy.harvesthero.ui.plantpedia

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkitacademy.harvesthero.data.PlantRepository
import com.bangkitacademy.harvesthero.data.local.entity.Plant

class PlantPediaViewModel(application: Application) : ViewModel() {

    private val mPlantRepository: PlantRepository = PlantRepository(application)

    val plants: LiveData<List<Plant>> = mPlantRepository.getAllPlant()

}