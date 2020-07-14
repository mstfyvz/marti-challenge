package com.yavuzmobile.martichallenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ViewPort(
    var northeast: Location,
    var southwest: Location
): Parcelable