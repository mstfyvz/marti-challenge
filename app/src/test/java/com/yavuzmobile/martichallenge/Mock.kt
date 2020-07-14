package com.yavuzmobile.martichallenge

import com.yavuzmobile.martichallenge.model.Candidates
import com.yavuzmobile.martichallenge.model.Geometry
import com.yavuzmobile.martichallenge.model.Location
import com.yavuzmobile.martichallenge.model.Place
import com.yavuzmobile.martichallenge.model.ViewPort


object Mock {
    val place = Place(
        listOf(
            Candidates(
                "Kadıköy, İstanbul",
                Geometry(
                    Location(49.000, 49.000),
                    ViewPort(Location(49.000, 49.000), Location(49.000, 49.000))
                ),
                "Kadıköy"
            )
        )
    )
}