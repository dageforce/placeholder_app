package com.testapp.babylonplaceholder.domain.usecase.user

import com.testapp.babylonplaceholder.domain.model.User
import com.testapp.babylonplaceholder.domain.repository.PlaceholderRepository
import com.testapp.babylonplaceholder.domain.usecase.SingleParameterisedUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(private val placeholderRepository: PlaceholderRepository) :
    SingleParameterisedUseCase<User, Int>() {

    override fun build(params: Int): Single<User> = placeholderRepository.getUser(params)

}
