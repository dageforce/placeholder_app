package com.testapp.babylonplaceholder.presentation.di

import android.app.Application
import android.content.Context
import com.testapp.babylonplaceholder.data.transformer.AndroidSchedulerTransformer
import com.testapp.babylonplaceholder.domain.transformer.SchedulerTransformer
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    internal fun provideApplicationContext(): Context = application

    @Provides
    internal fun providesSchedulerTransformer(): SchedulerTransformer =
        AndroidSchedulerTransformer(Schedulers.computation(), AndroidSchedulers.mainThread())
}
