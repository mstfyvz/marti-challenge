package com.yavuzmobile.martichallenge.feature.search

import com.yavuzmobile.martichallenge.BR
import com.yavuzmobile.martichallenge.R
import com.yavuzmobile.martichallenge.base.BaseListAdapter
import com.yavuzmobile.martichallenge.databinding.ItemPlaceLayoutBinding
import com.yavuzmobile.martichallenge.model.Candidates


class SearchAdapter : BaseListAdapter<Candidates, ItemPlaceLayoutBinding>() {
    override fun layoutResource(): Int = R.layout.item_place_layout
    override fun bindingVariableId(): Int = BR.candidate
}