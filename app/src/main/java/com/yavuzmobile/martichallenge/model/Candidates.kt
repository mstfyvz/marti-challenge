package com.yavuzmobile.martichallenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Candidates(
    var formatted_address: String,
    var geometry: Geometry,
    var name: String
): Parcelable