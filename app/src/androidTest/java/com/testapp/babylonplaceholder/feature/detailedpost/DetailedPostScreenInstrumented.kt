package com.testapp.babylonplaceholder.feature.detailedpost

import android.view.View
import com.agoda.kakao.*
import com.testapp.babylonplaceholder.R
import org.hamcrest.Matcher

class DetailedPostScreenInstrumented : Screen<DetailedPostScreenInstrumented>() {

    val postTitle = KTextView { withId(R.id.postTitle) }
    val posterName = KTextView { withId(R.id.posterName) }
    val totalComments = KTextView { withId(R.id.totalComments) }
    val commentList = KRecyclerView(builder = { withId(R.id.list) }, itemTypeBuilder = { itemType(::CommentItem) })

    class CommentItem(parent: Matcher<View>) : KRecyclerItem<CommentItem>(parent) {
        val title: KTextView = KTextView(parent) { withId(R.id.title) }
        val email: KTextView = KTextView(parent) { withId(R.id.email) }
        val body: KTextView = KTextView(parent) { withId(R.id.body) }
    }

    val snackBar = KSnackbar()
}
