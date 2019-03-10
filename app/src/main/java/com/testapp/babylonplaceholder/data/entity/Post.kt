package com.testapp.babylonplaceholder.data.entity

import com.testapp.babylonplaceholder.domain.model.Post

data class EntityPost(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)

fun EntityPost.toDomain(): Post {
    return Post(
        id = this.id,
        userId = this.userId,
        title = this.title.capitalize(),
        body = this.body.capitalize()
    )
}
