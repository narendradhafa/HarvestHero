package com.bangkitacademy.harvesthero.ui.add

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkitacademy.harvesthero.data.PlantRepository
import com.bangkitacademy.harvesthero.data.local.entity.Plant
import kotlinx.coroutines.launch

class AddViewModel(application: Application) : ViewModel() {

    private val mPlantRepository: PlantRepository = PlantRepository(application)

    fun insert(plant: Plant, callback: (Long) -> Unit) = viewModelScope.launch {
        val id = mPlantRepository.insert(plant)
        callback(id)
    }
}