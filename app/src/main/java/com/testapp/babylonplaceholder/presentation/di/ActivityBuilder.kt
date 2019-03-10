package com.testapp.babylonplaceholder.presentation.di

import com.testapp.babylonplaceholder.presentation.feature.detailedpost.DetailedPostActivity
import com.testapp.babylonplaceholder.presentation.feature.detailedpost.di.DetailedPostModule
import com.testapp.babylonplaceholder.presentation.feature.post.PostActivity
import com.testapp.babylonplaceholder.presentation.feature.post.di.PostModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [PostModule::class, DetailedPostModule::class])
interface ActivityBuilder {

    @ContributesAndroidInjector(modules = [PostModule::class])
    fun bindPostActivity(): PostActivity

    @ContributesAndroidInjector(modules = [DetailedPostModule::class])
    fun bindDetailedPostActivity(): DetailedPostActivity

}
