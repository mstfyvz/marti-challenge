package com.yavuzmobile.martichallenge.feature.search

import android.content.Context
import com.yavuzmobile.martichallenge.R
import com.yavuzmobile.martichallenge.ext.apiWrapper
import com.yavuzmobile.martichallenge.model.ApiResponse
import com.yavuzmobile.martichallenge.model.Place
import com.yavuzmobile.martichallenge.model.Search
import com.yavuzmobile.martichallenge.network.Api


interface SearchRepository {
    suspend fun getPlaces(request: Search): ApiResponse<Place>
}

class SearchRepositoryImpl(private val api: Api, private val context: Context) : SearchRepository {
    override suspend fun getPlaces(request: Search) = apiWrapper { api.getPlaces(request.input, context.getString(R.string.input_type), context.getString(R.string.fields), context.getString(R.string.google_maps_key)) }
}