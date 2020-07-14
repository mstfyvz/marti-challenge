package com.yavuzmobile.martichallenge.feature.search

import androidx.lifecycle.MutableLiveData
import com.yavuzmobile.martichallenge.R
import com.yavuzmobile.martichallenge.base.BaseViewModel
import com.yavuzmobile.martichallenge.ext.postFalse
import com.yavuzmobile.martichallenge.ext.postTrue
import com.yavuzmobile.martichallenge.model.Candidates
import com.yavuzmobile.martichallenge.model.Search
import com.yavuzmobile.martichallenge.model.YError


class SearchViewModel(private val repo: SearchRepository) : BaseViewModel() {

    val placesLive = MutableLiveData<List<Candidates>>()
    val searchEmptyLive = MutableLiveData<Boolean>()

    fun searchPlace(search: String) {
        getPlaces(search)
    }

    private fun getPlaces(search: String) {
        if(search.isEmpty()) {
            postError(YError.StringResError(R.string.is_not_empty_search))
            return
        }

        callService({repo.getPlaces(Search(search))},
            success = {
                if (it.candidates.isEmpty()) {
                    searchEmptyLive.postTrue()
                } else {
                    searchEmptyLive.postFalse()
                    placesLive.postValue(it.candidates)
                }
            })

    }

}