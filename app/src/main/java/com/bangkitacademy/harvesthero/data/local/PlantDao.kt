package com.bangkitacademy.harvesthero.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bangkitacademy.harvesthero.data.local.entity.Plant

@Dao
interface PlantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(plant: Plant) : Long

    @Delete
    fun delete(plant: Plant)

    @Query("SELECT * FROM plant WHERE id = :id")
    fun get(id: Int): LiveData<Plant>

    @Query("SELECT * FROM plant ORDER BY id ASC")
    fun getAll(): LiveData<List<Plant>>

    @Query("SELECT COUNT(*) FROM plant WHERE is_watered = 0")
    suspend fun countNotWateredPlants(): Int

    @Query("SELECT COUNT(*) FROM plant WHERE is_sick = 1")
    suspend fun countNotSickPlants(): Int

    @Query("SELECT COUNT(*) FROM plant WHERE is_fertilized = 0")
    suspend fun countNotFertilizedPlants(): Int

    @Query("SELECT * FROM plant")
    suspend fun getAllPlantsList(): List<Plant>  // New method to return a list directly

    @Query("UPDATE plant SET is_watered = :isWatered WHERE id = :plantId")
    suspend fun updateWatered(plantId: Int, isWatered: Boolean)

    @Query("UPDATE plant SET is_fertilized = :isFertilized WHERE id = :plantId")
    suspend fun updateFertilized(plantId: Int, isFertilized: Boolean)

    @Query("UPDATE plant SET time_to_water = :timeToWater WHERE id = :plantId")
    suspend fun updateTimeToWater(plantId: Int, timeToWater: Int)

    @Query("UPDATE plant SET time_to_fertilize = :timeToFertilize WHERE id = :plantId")
    suspend fun updateTimeToFertilize(plantId: Int, timeToFertilize: Int)

    @Query("DELETE FROM plant")
    suspend fun deleteAll()

    @Query("DELETE FROM plant WHERE id = :id")
    suspend fun deleteById(id: Int)
}