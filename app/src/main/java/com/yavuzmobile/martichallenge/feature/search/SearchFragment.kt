package com.yavuzmobile.martichallenge.feature.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.yavuzmobile.martichallenge.R
import com.yavuzmobile.martichallenge.base.BaseFragment
import com.yavuzmobile.martichallenge.ext.*
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment(R.layout.fragment_search) {

    private val vm: SearchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel(vm)

        val adapter = SearchAdapter()
        rvSearchResults.adapter = adapter

        etSearch.onChange(debounce(500L, vm.viewModelScope, vm::searchPlace))

        vm.placesLive.observeNotNull(viewLifecycleOwner) { adapter.items = it }
        vm.searchEmptyLive.observeNotNull(viewLifecycleOwner) {
            if (it) {
                rvSearchResults.hide()
                tvSearchEmpty.show()
            } else {
                rvSearchResults.show()
                tvSearchEmpty.hide()
            }
        }

        adapter.onItemClick = {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToMapsFragment(it))
        }
    }
}