package com.yavuzmobile.martichallenge.feature

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.yavuzmobile.martichallenge.BaseTest
import com.yavuzmobile.martichallenge.Mock
import com.yavuzmobile.martichallenge.R
import com.yavuzmobile.martichallenge.TestCoroutineRule
import com.yavuzmobile.martichallenge.feature.search.SearchRepository
import com.yavuzmobile.martichallenge.feature.search.SearchViewModel
import com.yavuzmobile.martichallenge.model.ApiResponse
import com.yavuzmobile.martichallenge.model.YError
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test


class SearchViewModelTest : BaseTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = TestCoroutineRule()

    private val repo: SearchRepository = mock()
    private val vm = SearchViewModel(repo)

    @Test
    fun testSearchPlaceValidation() {
        runBlocking {
            whenever(repo.getPlaces(any())).thenReturn(ApiResponse(error = YError.StringResError(R.string.is_not_empty_search)))

            vm.searchPlace("")


            val aError = vm.baseErrorLive.value as YError.StringResError
            assertEquals(aError.msgRes, R.string.is_not_empty_search)
            assertEquals(null, vm.placesLive.value)
        }
    }

    @Test
    fun testSearchPlace() {
        runBlocking {
            whenever(repo.getPlaces(any())).thenReturn(ApiResponse(Mock.place))

            vm.searchPlace("Kadıköy")

            assertEquals(false, vm.searchEmptyLive.value)
            assertEquals(Mock.place.candidates, vm.placesLive.value)
        }
    }

}