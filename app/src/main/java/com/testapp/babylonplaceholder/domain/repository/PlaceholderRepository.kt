package com.testapp.babylonplaceholder.domain.repository

import com.testapp.babylonplaceholder.domain.model.Comment
import com.testapp.babylonplaceholder.domain.model.Post
import com.testapp.babylonplaceholder.domain.model.User
import io.reactivex.Single

interface PlaceholderRepository {

    fun getUser(userId: Int): Single<User>

    fun getAllPosts(): Single<List<Post>>

    fun getPost(postId: Int): Single<Post>

    fun getComments(postId: Int) : Single<List<Comment>>

}
