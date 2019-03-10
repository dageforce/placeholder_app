package com.testapp.babylonplaceholder.presentation.feature.post

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.testapp.babylonplaceholder.R
import com.testapp.babylonplaceholder.databinding.ActivityPostBinding
import com.testapp.babylonplaceholder.domain.model.Post
import com.testapp.babylonplaceholder.presentation.app.obtainViewModel
import com.testapp.babylonplaceholder.presentation.app.snackBar
import com.testapp.babylonplaceholder.presentation.feature.detailedpost.DetailedPostActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class PostActivity : DaggerAppCompatActivity(), Observer<Post> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: PostViewModel

    private lateinit var binding: ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = obtainViewModel(viewModelFactory, PostViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post)
        binding.model = viewModel
        binding.lifecycleOwner = this

        viewModel.posts.observe(this, Observer {
            binding.list.scheduleLayoutAnimation()
        })

        viewModel.error.observe(this, Observer {
            snackBar(it)
        })

        viewModel.postSelected.observe(this, this)
    }

    override fun onChanged(post: Post) {
        val intent = DetailedPostActivity.forPost(this, post)
        ContextCompat.startActivity(this, intent, null)
    }
}
