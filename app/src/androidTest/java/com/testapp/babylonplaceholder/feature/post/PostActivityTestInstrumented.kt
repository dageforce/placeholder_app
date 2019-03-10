package com.testapp.babylonplaceholder.feature.post

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.testapp.babylonplaceholder.app.TestPlaceHolderApi
import com.testapp.babylonplaceholder.data.entity.EntityPost
import com.testapp.babylonplaceholder.feature.detailedpost.DetailedPostScreenInstrumented
import com.testapp.babylonplaceholder.presentation.feature.post.PostActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostActivityTestInstrumented {

    @get:Rule
    val rule = ActivityTestRule(PostActivity::class.java, false, false)

    private val postScreen = PostScreenInstrumented()
    private val detailedPostScren = DetailedPostScreenInstrumented()
    private val defaultPost = EntityPost(id = 1, userId = 2, title = "Default Post", body = "Default body")

    @Before
    fun setUp() {
        TestPlaceHolderApi.allPosts = listOf(defaultPost)
    }

    @Test
    fun when_screen_loads_THEN_display_list_of_posts() {

        rule.launchActivity(null)

        postScreen {
            postList {
                isDisplayed()
            }
        }
    }

    @Test
    fun when_screen_loads_THEN_first_item_title_and_body_correct() {
        rule.launchActivity(null)

        postScreen {
            postList {
                firstChild<PostScreenInstrumented.PostItem> {
                    title { hasText(defaultPost.title) }
                    body { hasText(defaultPost.body) }
                }
            }
        }
    }

    @Test
    fun when_network_error_THEN_show_snack_bar_with_error() {
        TestPlaceHolderApi.triggerError = true

        rule.launchActivity(null)

        postScreen {
            snackBar {
                text { hasText("Error thrown") }
            }
        }

        TestPlaceHolderApi.triggerError = false
    }

    @Test
    fun when_post_selected_then_show_details() {
        rule.launchActivity(null)

        postScreen {
            postList {
                firstChild<PostScreenInstrumented.PostItem> {
                    click()
                }
            }
            detailedPostScren {
                postTitle { hasText(defaultPost.title) }
            }
        }
    }
}
