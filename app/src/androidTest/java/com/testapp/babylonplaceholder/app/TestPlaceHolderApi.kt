package com.testapp.babylonplaceholder.app

import com.testapp.babylonplaceholder.data.entity.EntityComment
import com.testapp.babylonplaceholder.data.entity.EntityPost
import com.testapp.babylonplaceholder.data.entity.EntityUser
import com.testapp.babylonplaceholder.data.network.PlaceholderApi
import io.reactivex.Single

class TestPlaceHolderApi : PlaceholderApi {

    companion object {
        var allPosts = emptyList<EntityPost>()
        val post = EntityPost(1, 2, "Title", "Body text")
        var user = EntityUser(1, "Jannie", "Jannie", "jan@jan.com")
        var comments = emptyList<EntityComment>()
        var triggerError = false
    }

    override fun getAllPosts(): Single<List<EntityPost>> {
        if (triggerError) {
            return Single.error(Throwable("Error thrown"))
        }
        return Single.just(allPosts)
    }

    override fun getPost(postId: Int): Single<EntityPost> {
        if (triggerError) {
            return Single.error(Throwable("Error thrown"))
        }
        return Single.just(post)
    }

    override fun getUser(userId: Int): Single<EntityUser> {
        if (triggerError) {
            return Single.error(Throwable("Error thrown"))
        }
        return Single.just(user)
    }

    override fun getComments(postId: Int): Single<List<EntityComment>> {
        if (triggerError) {
            return Single.error(Throwable("Error thrown"))
        }

        return Single.just(comments)
    }

}