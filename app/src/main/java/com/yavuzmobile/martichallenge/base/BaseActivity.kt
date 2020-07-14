package com.yavuzmobile.martichallenge.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.Places
import com.yavuzmobile.martichallenge.R
import com.yavuzmobile.martichallenge.ext.observeNotNull
import com.yavuzmobile.martichallenge.model.YError


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        // Initialize the SDK
        Places.initialize(applicationContext, getString(R.string.google_maps_key))
        // Create a new PlacesClient instance
        Places.createClient(this)
    }

    fun bindViewModel(vm: BaseViewModel) {
        vm.baseErrorLive.observeNotNull(this) { showError(it) }
    }

    private fun showError(error: YError) {
        when (error) {
            is YError.StringResError -> showErrorMessage(getString(error.msgRes))
        }
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}