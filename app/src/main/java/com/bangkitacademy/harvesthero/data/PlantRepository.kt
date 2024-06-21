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

    suspend fun updatePlantWatered(plantId: Int, isWatered: Boolean) {
        mPlantDao.updateWatered(plantId, isWatered)
    }

    suspend fun updatePlantFertilized(plantId: Int, isFertilized: Boolean) {
        mPlantDao.updateFertilized(plantId, isFertilized)
    }

    suspend fun updateTimeToWater(plantId: Int, timeToWater: Int) {
        mPlantDao.updateTimeToWater(plantId, timeToWater)
    }

    suspend fun updateTimeToFertilize(plantId: Int, timeToFertilize: Int) {
        mPlantDao.updateTimeToFertilize(plantId, timeToFertilize)
    }

    suspend fun countWateredPlants(): Int = withContext(Dispatchers.IO) {
        mPlantDao.countNotWateredPlants()
    }

    suspend fun countSickPlants(): Int = withContext(Dispatchers.IO) {
        mPlantDao.countNotSickPlants()
    }

    suspend fun countFertilizedPlants(): Int = withContext(Dispatchers.IO) {
        mPlantDao.countNotFertilizedPlants()
    }

    fun delete(plant: Plant) {
        executorService.execute { mPlantDao.delete(plant) }
    }

    suspend fun deleteById(id: Int) = withContext(Dispatchers.IO) {
        mPlantDao.deleteById(id)
    }

    suspend fun incrementTimesForAllPlants() {
        val plants = mPlantDao.getAllPlantsList()
        for (plant in plants) {
            val newTimeToWater = (plant.timeToWater ?: 0) + 1
            val newTimeToFertilize = (plant.timeToFertilize ?: 0) + 1

            if (newTimeToWater > 24) {
                mPlantDao.updateWatered(plant.id, false)
            }

            if (newTimeToFertilize > 24) {
                mPlantDao.updateFertilized(plant.id, false)
            }

            mPlantDao.updateTimeToWater(plant.id, newTimeToWater)
            mPlantDao.updateTimeToFertilize(plant.id, newTimeToFertilize)
        }
    }
}