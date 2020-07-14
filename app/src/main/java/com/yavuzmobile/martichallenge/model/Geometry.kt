package com.yavuzmobile.martichallenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Geometry(
    var location: Location,
    var viewport: ViewPort
): Parcelable