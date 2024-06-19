package com.bangkitacademy.harvesthero.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkitacademy.harvesthero.data.PlantRepository
import com.bangkitacademy.harvesthero.data.local.entity.Plant
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : ViewModel() {

    private val mPlantRepository: PlantRepository = PlantRepository(application)

    fun get(id: Int): LiveData<Plant> {
        return mPlantRepository.getPlant(id)
    }

    fun deleteById(id: Int) = viewModelScope.launch {
        mPlantRepository.deleteById(id)
    }}