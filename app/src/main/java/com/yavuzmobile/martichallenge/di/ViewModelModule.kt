package com.yavuzmobile.martichallenge.di

import com.yavuzmobile.martichallenge.feature.search.SearchViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val viewModelModule = module {
    viewModel { SearchViewModel(get()) }
}