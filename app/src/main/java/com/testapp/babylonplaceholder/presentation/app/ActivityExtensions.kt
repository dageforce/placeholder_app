package com.testapp.babylonplaceholder.presentation.app

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar

fun <T : ViewModel> AppCompatActivity.obtainViewModel(factory: ViewModelProvider.Factory, viewModelClass: Class<T>) =
    ViewModelProviders.of(this, factory).get(viewModelClass)

fun AppCompatActivity.snackBar(
    text: String,
    duration: Int = Snackbar.LENGTH_SHORT,
    init: Snackbar.() -> Unit = {}
) {
    findViewById<View>(android.R.id.content).snackBar(text, duration, init)
}

inline fun View.snackBar(
    message: String,
    length: Int = Snackbar.LENGTH_LONG,
    init: Snackbar.() -> Unit
) {
    val snack = Snackbar.make(this, message, length)
    snack.init()
    snack.show()
}
