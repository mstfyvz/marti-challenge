package com.yavuzmobile.martichallenge.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonProvider {
    private val gson = GsonBuilder().create()

    fun get(): Gson = gson
}