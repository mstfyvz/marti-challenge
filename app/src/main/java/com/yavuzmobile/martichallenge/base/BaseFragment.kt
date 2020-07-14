package com.yavuzmobile.martichallenge.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment


abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    fun bindViewModel(vm: BaseViewModel) = (activity as? BaseActivity)?.bindViewModel(vm)
}