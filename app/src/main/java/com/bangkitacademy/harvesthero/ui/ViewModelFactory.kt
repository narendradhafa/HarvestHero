package com.bangkitacademy.harvesthero.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkitacademy.harvesthero.ui.add.AddViewModel
import com.bangkitacademy.harvesthero.ui.detail.DetailViewModel
import com.bangkitacademy.harvesthero.ui.home.HomeViewModel
import com.bangkitacademy.harvesthero.ui.myplants.MyPlantsViewModel
import com.bangkitacademy.harvesthero.ui.plantpedia.PlantPediaViewModel

class ViewModelFactory(
    private val mApplication: Application
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(mApplication) as T
            }
            modelClass.isAssignableFrom(MyPlantsViewModel::class.java) -> {
                MyPlantsViewModel(mApplication) as T
            }
            modelClass.isAssignableFrom(PlantPediaViewModel::class.java) -> {
                PlantPediaViewModel(mApplication) as T
            }
            modelClass.isAssignableFrom(AddViewModel::class.java) -> {
                AddViewModel(mApplication) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mApplication) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}