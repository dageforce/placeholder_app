package com.testapp.babylonplaceholder.data.network

import com.testapp.babylonplaceholder.data.entity.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaceholderApi {

    @GET("posts/")
    fun getAllPosts(): Single<List<EntityPost>>

    @GET("posts/{postId}")
    fun getPost(@Path("postId") postId: Int): Single<EntityPost>

    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: Int): Single<EntityUser>

    @GET("comments")
    fun getComments(@Query("postId") postId: Int): Single<List<EntityComment>>

}
