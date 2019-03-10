package com.testapp.babylonplaceholder.presentation.app

import com.testapp.babylonplaceholder.presentation.di.ApplicationModule
import com.testapp.babylonplaceholder.presentation.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class PlaceholderApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
       return DaggerApplicationComponent.builder()
           .application(this)
           .applicationModule(ApplicationModule(this))
           .build()
           .also { it.inject(this)}
    }

}
