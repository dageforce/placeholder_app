package com.testapp.babylonplaceholder.presentation.feature.post

import androidx.lifecycle.MutableLiveData
import com.testapp.babylonplaceholder.R
import com.testapp.babylonplaceholder.domain.model.Post
import com.testapp.babylonplaceholder.domain.usecase.post.GetAllPostsUseCase
import com.testapp.babylonplaceholder.presentation.app.DisposableViewModel
import com.testapp.babylonplaceholder.presentation.app.SingleLiveEvent
import io.reactivex.rxkotlin.subscribeBy
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.itemBindingOf

class PostViewModel(private val getAllPostsUseCase: GetAllPostsUseCase) : DisposableViewModel(), PostSelectionHandler {

    val posts = MutableLiveData<List<Post>>()
    val error = SingleLiveEvent<String>()
    val postSelected = SingleLiveEvent<Post>()
    val itemBinding = itemBindingOf<Post>(BR.model, R.layout.card_post).bindExtra(BR.cardHandler, this)

    init {
        refresh()
    }

    fun refresh() {
        getAllPostsUseCase.get().onErrorReturn {
            error.postValue(it.localizedMessage)
            emptyList()
        }.subscribeBy(
            onSuccess = posts::postValue
        ).disposedBy(this)
    }

    override fun onPostSelected(post: Post) {
        postSelected.postValue(post)
    }
}
