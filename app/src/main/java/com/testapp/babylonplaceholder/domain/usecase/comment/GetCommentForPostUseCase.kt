package com.testapp.babylonplaceholder.domain.usecase.comment

import com.testapp.babylonplaceholder.domain.model.Comment
import com.testapp.babylonplaceholder.domain.model.Post
import com.testapp.babylonplaceholder.domain.repository.PlaceholderRepository
import com.testapp.babylonplaceholder.domain.usecase.SingleParameterisedUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetCommentForPostUseCase @Inject constructor(private val placeholderRepository: PlaceholderRepository) :
    SingleParameterisedUseCase<List<Comment>, Post>() {

    override fun build(params: Post): Single<List<Comment>> = placeholderRepository.getComments(params.id)

}
