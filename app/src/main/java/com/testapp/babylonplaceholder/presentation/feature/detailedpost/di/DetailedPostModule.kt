package com.testapp.babylonplaceholder.presentation.feature.detailedpost.di

import androidx.lifecycle.ViewModel
import com.testapp.babylonplaceholder.domain.usecase.comment.GetCommentForPostUseCase
import com.testapp.babylonplaceholder.domain.usecase.user.GetUserByIdUseCase
import com.testapp.babylonplaceholder.presentation.di.ViewModelKey
import com.testapp.babylonplaceholder.presentation.feature.detailedpost.DetailedPostViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [DetailedPostModule.ViewModelModule::class])
class DetailedPostModule {

    @Module
    class ViewModelModule {
        @Provides
        @IntoMap
        @ViewModelKey(DetailedPostViewModel::class)
        fun provideViewModel(
            getCommentForPostUseCase: GetCommentForPostUseCase,
            getUserByIdUseCase: GetUserByIdUseCase
        ): ViewModel =
            DetailedPostViewModel(getCommentForPostUseCase, getUserByIdUseCase)
    }

}
