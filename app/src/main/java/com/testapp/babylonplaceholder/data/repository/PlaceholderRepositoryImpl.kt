package com.testapp.babylonplaceholder.data.repository

import com.testapp.babylonplaceholder.data.entity.toDomain
import com.testapp.babylonplaceholder.data.network.PlaceholderApi
import com.testapp.babylonplaceholder.domain.model.Comment
import com.testapp.babylonplaceholder.domain.model.Post
import com.testapp.babylonplaceholder.domain.model.User
import com.testapp.babylonplaceholder.domain.repository.PlaceholderRepository
import io.reactivex.Single

class PlaceholderRepositoryImpl(private val api: PlaceholderApi) : PlaceholderRepository {
    override fun getUser(userId: Int): Single<User> = api.getUser(userId).map { it.toDomain() }

    override fun getAllPosts(): Single<List<Post>> = api.getAllPosts().map { items -> items.map { it.toDomain() } }

    override fun getPost(postId: Int): Single<Post> = api.getPost(postId).map { it.toDomain() }

    override fun getComments(postId: Int): Single<List<Comment>> =
        api.getComments(postId).map { items -> items.map { it.toDomain() } }
}
