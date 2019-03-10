package com.testapp.babylonplaceholder.feature.detailedpost

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.testapp.babylonplaceholder.app.test
import com.testapp.babylonplaceholder.domain.model.Comment
import com.testapp.babylonplaceholder.domain.model.Post
import com.testapp.babylonplaceholder.domain.model.User
import com.testapp.babylonplaceholder.domain.usecase.comment.GetCommentForPostUseCase
import com.testapp.babylonplaceholder.domain.usecase.user.GetUserByIdUseCase
import com.testapp.babylonplaceholder.presentation.feature.detailedpost.DetailedPostViewModel
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class DetailedPostViewModelTest {

    private val defaultUser = User(1, "Jannie", "Jannie", "jannie@bla.bla")
    private val defaultPost = Post(id = 1, userId = 2, title = "Default Post", body = "Default body")

    private val getCommentForPostUseCase: GetCommentForPostUseCase = mock {
        on { get(any()) }.thenReturn(Single.just(emptyList()))
    }

    private val getUserByIdUseCase: GetUserByIdUseCase = mock {
        on { get(any()) }.thenReturn(Single.just(defaultUser))
    }

    private val viewModel = DetailedPostViewModel(getCommentForPostUseCase, getUserByIdUseCase)

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun `HAVING requested comments for a post WHEN useCase results in error THEN return empty list and emit error message string`() {

        //having
        whenever(getCommentForPostUseCase.get(any())).thenReturn(Single.error(IllegalStateException("Something went wrong!")))

        val errorObserver = viewModel.error.test()
        val commmentsObserver = viewModel.comments.test()

        //when
        viewModel.setPost(defaultPost)

        //then
        errorObserver.assertLatest { it == "Something went wrong!" }
        commmentsObserver.assertLatest { it.isEmpty() }
    }

    @Test
    fun `HAVING requested a user for a post WHEN useCase results in error THEN return error message string and empty username`() {

        //having
        whenever(getUserByIdUseCase.get(any())).thenReturn(Single.error(IllegalStateException("Something went wrong!")))

        val errorObserver = viewModel.error.test()
        val usernameObserver = viewModel.username.test()

        //when
        viewModel.setPost(defaultPost)

        //then
        errorObserver.assertLatest { it == "Something went wrong!" }
        usernameObserver.assertLatest { it == "" }
    }

    @Test
    fun `HAVING requested comments WHEN useCase results in 5 comments THEN return list of 5 comments with no error`() {

        //having
        val size = 5
        whenever(getCommentForPostUseCase.get(any())).thenReturn(Single.just(generateComments(size)))

        val errorObserver = viewModel.error.test()
        val commentObserver = viewModel.comments.test()

        //when
        viewModel.setPost(defaultPost)

        //then
        errorObserver.assertIsEmpty()
        commentObserver.assertLatest { it.size == size }
    }

    @Test
    fun `HAVING requested comments WHEN useCase results in 5 comments THEN return total comments as 5 comments`() {

        //having
        val size = 5
        whenever(getCommentForPostUseCase.get(any())).thenReturn(Single.just(generateComments(size)))

        val errorObserver = viewModel.error.test()
        val commentObserver = viewModel.totalComments.test()

        //when
        viewModel.setPost(defaultPost)

        //then
        errorObserver.assertIsEmpty()
        commentObserver.assertLatest { it == "Comments: $size" }
    }

    @Test
    fun `HAVING requested a user for a post WHEN useCase results in a valid user THEN return username string`() {

        //having
        val errorObserver = viewModel.error.test()
        val usernameObserver = viewModel.username.test()

        //when
        viewModel.setPost(defaultPost)

        //then
        errorObserver.assertIsEmpty()
        usernameObserver.assertLatest { it == "By ${defaultUser.username}" }
    }

    private fun generateComments(count: Int): List<Comment> {
        return (1..count).map(::generateComment)
    }

    private fun generateComment(identifier: Int): Comment {
        return Comment(
            id = identifier,
            postId = identifier,
            name = "Name $identifier",
            email = "Email $identifier",
            body = "Body $identifier"
        )
    }
}
