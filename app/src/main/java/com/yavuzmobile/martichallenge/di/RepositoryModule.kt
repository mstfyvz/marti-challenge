package com.yavuzmobile.martichallenge.di

import com.yavuzmobile.martichallenge.feature.search.SearchRepository
import com.yavuzmobile.martichallenge.feature.search.SearchRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module


val dataModule = module {
    single<SearchRepository> { SearchRepositoryImpl(get(), androidContext()) }
}