package com.yavuzmobile.martichallenge.network

import com.yavuzmobile.martichallenge.model.Place
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
    @GET("place/findplacefromtext/json")
    suspend fun getPlaces(@Query("input") input: String, @Query("inputtype") inputType: String, @Query("fields") fields: String, @Query("key") key: String): Response<Place>
}