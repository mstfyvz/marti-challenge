package com.yavuzmobile.martichallenge.model

import androidx.annotation.StringRes

sealed class YError() {
    object Network : YError()
    object Timeout : YError()
    object Generic : YError()
    class Business(val msg: String, code: Int = 0) : YError()
    object Authorization : YError()
    class StringResError(@StringRes val msgRes: Int) : YError()
}