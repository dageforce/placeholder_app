package com.testapp.babylonplaceholder.data.entity

import com.testapp.babylonplaceholder.domain.model.Comment

data class EntityComment(
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String
)

fun EntityComment.toDomain(): Comment {
    return Comment(
        id = this.id,
        postId = this.postId,
        name = this.name.capitalize(),
        email = this.email,
        body = this.body.capitalize()
    )
}
