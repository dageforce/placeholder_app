package com.testapp.babylonplaceholder.domain.usecase.post

import com.testapp.babylonplaceholder.domain.model.Post
import com.testapp.babylonplaceholder.domain.repository.PlaceholderRepository
import com.testapp.babylonplaceholder.domain.usecase.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(private val placeholderRepository: PlaceholderRepository) :
    SingleUseCase<List<Post>>() {

    override fun build(): Single<List<Post>> = placeholderRepository.getAllPosts()

}
