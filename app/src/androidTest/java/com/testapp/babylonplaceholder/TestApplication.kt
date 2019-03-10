package com.testapp.babylonplaceholder

import com.testapp.babylonplaceholder.app.DaggerTestAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class TestApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerTestAppComponent.builder()
            .application(this)
            .build()
            .also {
                it.inject(this)
            }
    }
}
