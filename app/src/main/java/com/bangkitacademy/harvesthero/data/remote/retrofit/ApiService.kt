package com.bangkitacademy.harvesthero.data.remote.retrofit

import retrofit2.http.GET

interface ApiService {

    @GET("plants")
    suspend fun getAllPlants()
}