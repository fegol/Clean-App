package com.example.data.network

import com.example.data.network.response.ElementsResponse
import retrofit2.http.GET

interface Api {

    @GET("/v1/91a858b0-fc1b-4668-a7a7-0919cb40e405")
    suspend fun getData(): ElementsResponse
}