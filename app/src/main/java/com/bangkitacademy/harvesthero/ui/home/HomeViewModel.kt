package com.bangkitacademy.harvesthero.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bangkitacademy.harvesthero.data.PlantRepository

class HomeViewModel(application: Application) : ViewModel() {

    private val mPlantRepository: PlantRepository = PlantRepository(application)

    val wateredPlantsCount = liveData {
        emit(mPlantRepository.countWateredPlants())
    }

    val sickPlantsCount = liveData {
        emit(mPlantRepository.countSickPlants())
    }

    val fertilizedPlantsCount = liveData {
        emit(mPlantRepository.countFertilizedPlants())
    }
}