package com.bangkitacademy.harvesthero.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant")
data class Plant (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "plant_name")
    val plantName: String?,

    @ColumnInfo(name = "plant_type")
    val plantType: String,

    @ColumnInfo(name = "image_string")
    val imageString: String,

    @ColumnInfo(name = "desc")
    val desc: String?,

    @ColumnInfo(name = "current_growth")
    val currentGrowth: Boolean?,

    @ColumnInfo(name = "time_to_growth")
    val timeToGrowth: Int?,

    @ColumnInfo(name = "is_watered")
    val isWatered: Boolean?,

    @ColumnInfo(name = "time_to_water")
    val timeToWater: Int?,

    @ColumnInfo(name = "is_fertilized")
    val isFertilized: Boolean?,

    @ColumnInfo(name = "time_to_fertilize")
    val timeToFertilize: Int?,

    @ColumnInfo(name = "is_sick")
    val isSick: Boolean?,
)
