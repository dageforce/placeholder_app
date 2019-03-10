package com.testapp.babylonplaceholder.data.entity

import com.testapp.babylonplaceholder.domain.model.User

data class EntityUser(
    val id: Int,
    val name: String,
    val username: String,
    val email: String
)

fun EntityUser.toDomain(): User {
    return User(
        id = this.id,
        name = this.name.capitalize(),
        username = this.username.capitalize(),
        email = this.email
    )
}
