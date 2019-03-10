package com.testapp.babylonplaceholder.presentation.feature.post.di

import androidx.lifecycle.ViewModel
import com.testapp.babylonplaceholder.domain.usecase.post.GetAllPostsUseCase
import com.testapp.babylonplaceholder.presentation.di.ViewModelKey
import com.testapp.babylonplaceholder.presentation.feature.post.PostViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [PostModule.ViewModelModule::class])
class PostModule {

    @Module
    class ViewModelModule {
        @Provides
        @IntoMap
        @ViewModelKey(PostViewModel::class)
        fun provideViewModel(getAllPostsUseCase: GetAllPostsUseCase): ViewModel = PostViewModel(getAllPostsUseCase)
    }

}
