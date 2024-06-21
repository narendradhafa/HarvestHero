package com.bangkitacademy.harvesthero.helper

import android.app.Application
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bangkitacademy.harvesthero.data.PlantRepository

class TimeUpdateWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val repository = PlantRepository(applicationContext as Application)
        repository.incrementTimesForAllPlants()
        return Result.success()
    }
}