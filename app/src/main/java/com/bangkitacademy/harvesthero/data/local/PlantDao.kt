package com.bangkitacademy.harvesthero.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bangkitacademy.harvesthero.data.local.entity.Plant

interface PlantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlant(plant: Plant)

    @Delete
    fun deletePlant(plant: Plant)

    @Query("SELECT * FROM plant")
    fun getAllPlant(): List<Plant>

    @Query("DELETE FROM plant")
    suspend fun deleteAll()
}