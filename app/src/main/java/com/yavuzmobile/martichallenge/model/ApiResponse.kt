package com.yavuzmobile.martichallenge.model

data class ApiResponse<T>(val success: T? = null, val error: YError? = null)