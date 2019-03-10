package com.testapp.babylonplaceholder.feature.detailedpost

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.testapp.babylonplaceholder.app.TestPlaceHolderApi
import com.testapp.babylonplaceholder.data.entity.EntityComment
import com.testapp.babylonplaceholder.data.entity.EntityPost
import com.testapp.babylonplaceholder.data.entity.toDomain
import com.testapp.babylonplaceholder.presentation.feature.detailedpost.DetailedPostActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailedPostActivityTestInstrumented {

    @get:Rule
    val rule = ActivityTestRule(DetailedPostActivity::class.java, false, false)

    private val detailedPostScreen = DetailedPostScreenInstrumented()
    private val defaultPost = EntityPost(id = 1, userId = 2, title = "Default Post", body = "Default body")
    private val defaultComment =
        EntityComment(id = 1, postId = 1, name = "Comment Name", email = "Email", body = "Body")

    @Before
    fun setUp() {
        TestPlaceHolderApi.allPosts = listOf(defaultPost)
        TestPlaceHolderApi.comments = listOf(defaultComment)
    }

    @Test
    fun when_screen_loads_THEN_display_correct_post_title_and_user() {

        rule.launchActivity(
            DetailedPostActivity.forPost(
                ApplicationProvider.getApplicationContext<Context>(),
                defaultPost.toDomain()
            )
        )

        detailedPostScreen {
            postTitle { hasText(defaultPost.title) }
            posterName {
                hasText("By ${TestPlaceHolderApi.user.username}")
            }
        }
    }

    @Test
    fun when_screen_loads_THEN_display_comment_info_correctly() {

        rule.launchActivity(
            DetailedPostActivity.forPost(
                ApplicationProvider.getApplicationContext<Context>(),
                defaultPost.toDomain()
            )
        )

        detailedPostScreen {
            totalComments { hasText("Comments: ${TestPlaceHolderApi.comments.size}") }
            commentList {
                firstChild<DetailedPostScreenInstrumented.CommentItem> {
                    title { hasText(defaultComment.name) }
                    body { hasText(defaultComment.body) }
                    email { hasText(defaultComment.email) }
                }
            }
        }
    }
}
