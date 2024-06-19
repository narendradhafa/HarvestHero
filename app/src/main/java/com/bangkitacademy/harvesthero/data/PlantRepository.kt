package com.bangkitacademy.harvesthero.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.bangkitacademy.harvesthero.data.local.PlantDao
import com.bangkitacademy.harvesthero.data.local.PlantDatabase
import com.bangkitacademy.harvesthero.data.local.entity.Plant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PlantRepository(application: Application) {

    private val mPlantDao: PlantDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = PlantDatabase.getDatabase(application)
        mPlantDao = db.plantDao()
    }

    fun getAllPlant(): LiveData<List<Plant>> = mPlantDao.getAll()

    fun getPlant(id: Int): LiveData<Plant> = mPlantDao.get(id)

    suspend fun insert(plant: Plant): Long {
        return withContext(Dispatchers.IO) {
            mPlantDao.insert(plant)
        }
    }

    fun delete(plant: Plant) {
        executorService.execute { mPlantDao.delete(plant) }
    }

    suspend fun deleteById(id: Int) = withContext(Dispatchers.IO) {
        mPlantDao.deleteById(id)
    }

    fun update(plant: Plant) {
        executorService.execute { mPlantDao.update(plant) }
    }
}