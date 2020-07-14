package com.yavuzmobile.martichallenge.feature.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.yavuzmobile.martichallenge.R
import com.yavuzmobile.martichallenge.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : BaseFragment(R.layout.fragment_detail) {

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.candidates.let {
            tvValueLocationAddress.text = it?.formatted_address
            tvValueLocationCoordinates.text = "${it?.geometry?.location?.lat},\n ${it?.geometry?.location?.lng}"
        }
    }
}