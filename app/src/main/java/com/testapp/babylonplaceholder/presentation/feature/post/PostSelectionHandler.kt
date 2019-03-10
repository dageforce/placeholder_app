package com.testapp.babylonplaceholder.presentation.feature.post

import com.testapp.babylonplaceholder.domain.model.Post

interface PostSelectionHandler {
    fun onPostSelected(post: Post)
}
