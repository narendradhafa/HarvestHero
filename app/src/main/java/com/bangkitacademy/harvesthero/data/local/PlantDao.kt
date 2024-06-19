package com.bangkitacademy.harvesthero.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bangkitacademy.harvesthero.data.local.entity.Plant

@Dao
interface PlantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(plant: Plant)

    @Update
    fun update(plant: Plant)

    @Delete
    fun delete(plant: Plant)

    @Query("SELECT * FROM plant ORDER BY id ASC")
    fun getAll(): LiveData<List<Plant>>

    @Query("DELETE FROM plant")
    suspend fun deleteAll()
}