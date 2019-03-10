package com.testapp.babylonplaceholder.feature.post

import android.view.View
import com.agoda.kakao.*
import com.testapp.babylonplaceholder.R
import org.hamcrest.Matcher

class PostScreenInstrumented : Screen<PostScreenInstrumented>() {

    val postList = KRecyclerView(builder = { withId(R.id.list) }, itemTypeBuilder = { itemType(::PostItem) })

    class PostItem(parent: Matcher<View>) : KRecyclerItem<PostItem>(parent) {
        val title: KTextView = KTextView(parent) { withId(R.id.postTitle) }
        val body: KTextView = KTextView(parent) { withId(R.id.body) }
    }

    val snackBar = KSnackbar()
}
