package com.testapp.babylonplaceholder.feature.post

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.testapp.babylonplaceholder.app.test
import com.testapp.babylonplaceholder.domain.model.Post
import com.testapp.babylonplaceholder.domain.usecase.post.GetAllPostsUseCase
import com.testapp.babylonplaceholder.presentation.feature.post.PostViewModel
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class PostViewModelTest {

    private val getAllPostsUseCase: GetAllPostsUseCase = mock {
        on { get() }.thenReturn(Single.just(emptyList()))
    }
    private val viewModel = PostViewModel(getAllPostsUseCase)

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun `HAVING requested posts WHEN useCase results in error THEN return empty list and emit error message string`() {

        //having
        whenever(getAllPostsUseCase.get()).thenReturn(Single.error(IllegalStateException("Something went wrong!")))

        val errorObserver = viewModel.error.test()
        val postObserver = viewModel.posts.test()

        //when
        viewModel.refresh()

        //then
        errorObserver.assertLatest { it == "Something went wrong!" }
        postObserver.assertLatest { it.isEmpty() }
    }

    @Test
    fun `HAVING requested posts WHEN useCase results in 5 posts THEN return list of 5 posts with no error`() {

        //having
        val size = 5
        whenever(getAllPostsUseCase.get()).thenReturn(Single.just(generatePosts(size)))

        val errorObserver = viewModel.error.test()
        val postObserver = viewModel.posts.test()

        //when
        viewModel.refresh()

        //then
        errorObserver.assertIsEmpty()
        postObserver.assertLatest { it.size == size }
    }

    @Test
    fun `HAVING selected the 2rd post WHEN useCase resulted in 3 posts THEN emit 2nd post as selected post`() {

        //having
        val size = 3
        val posts = generatePosts(size)

        whenever(getAllPostsUseCase.get()).thenReturn(Single.just(posts))

        val selectionObserver = viewModel.postSelected.test()

        //when
        viewModel.onPostSelected(posts[1])

        //then
        selectionObserver.assertLatest { it.id == 2 }
    }

    private fun generatePosts(count: Int): List<Post> {
        return (1..count).map(::generatePost)
    }

    private fun generatePost(identifier: Int): Post {
        return Post(
            id = identifier,
            userId = identifier,
            title = "Title $identifier",
            body = "Body $identifier"
        )
    }

}
