package com.bangkitacademy.harvesthero.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.bangkitacademy.harvesthero.data.local.PlantDao
import com.bangkitacademy.harvesthero.data.local.PlantDatabase
import com.bangkitacademy.harvesthero.data.local.entity.Plant
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PlantRepository(application: Application) {

    private val mPlantDao: PlantDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = PlantDatabase.getDatabase(application)
        mPlantDao = db.plantDao()
    }

    fun getAllNotes(): LiveData<List<Plant>> = mPlantDao.getAll()

    fun insert(plant: Plant) {
        executorService.execute { mPlantDao.insert(plant) }
    }

    fun delete(plant: Plant) {
        executorService.execute { mPlantDao.delete(plant) }
    }

    fun update(plant: Plant) {
        executorService.execute { mPlantDao.update(plant) }
    }
}