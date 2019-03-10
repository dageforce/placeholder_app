package com.testapp.babylonplaceholder.presentation.di

import android.app.Application
import com.testapp.babylonplaceholder.data.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ActivityBuilder::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {
    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun applicationModule(applicationModule: ApplicationModule): Builder

        fun build(): ApplicationComponent

    }
}
