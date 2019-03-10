package com.testapp.babylonplaceholder.presentation.feature.detailedpost

import androidx.lifecycle.MutableLiveData
import com.testapp.babylonplaceholder.R
import com.testapp.babylonplaceholder.domain.model.Comment
import com.testapp.babylonplaceholder.domain.model.Post
import com.testapp.babylonplaceholder.domain.usecase.comment.GetCommentForPostUseCase
import com.testapp.babylonplaceholder.domain.usecase.user.GetUserByIdUseCase
import com.testapp.babylonplaceholder.presentation.app.DisposableViewModel
import com.testapp.babylonplaceholder.presentation.app.SingleLiveEvent
import io.reactivex.rxkotlin.subscribeBy
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.itemBindingOf

class DetailedPostViewModel(
    private val getCommentForPostUseCase: GetCommentForPostUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase
) : DisposableViewModel() {

    val comments = MutableLiveData<List<Comment>>()
    val totalComments = MutableLiveData<String>()
    val username = MutableLiveData<String>()
    val post = MutableLiveData<Post>()
    val error = SingleLiveEvent<String>()
    val itemBinding = itemBindingOf<Comment>(BR.model, R.layout.card_comment)

    fun setPost(post: Post) {
        this.post.postValue(post)
        loadComments(post)
        loadUser(post.userId)
    }

    private fun loadComments(post: Post) {
        getCommentForPostUseCase.get(post).onErrorReturn {
            error.postValue(it.localizedMessage)
            emptyList()
        }.subscribeBy(
            onSuccess = ::handleNewComments
        ).disposedBy(this)
    }

    private fun loadUser(userId: Int) {
        getUserByIdUseCase.get(userId).subscribeBy(
            onSuccess = { username.postValue("By ${it.username}") },
            onError = {
                username.postValue("")
                error.postValue(it.localizedMessage)
            }
        ).disposedBy(this)
    }

    private fun handleNewComments(comments: List<Comment>) {
        this.comments.postValue(comments)
        this.totalComments.postValue("Comments: ${comments.size}")
    }
}
