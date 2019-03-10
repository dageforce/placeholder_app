package com.testapp.babylonplaceholder.presentation.feature.detailedpost

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.testapp.babylonplaceholder.R
import com.testapp.babylonplaceholder.databinding.ActivityDetailedpostBinding
import com.testapp.babylonplaceholder.domain.model.Post
import com.testapp.babylonplaceholder.presentation.app.obtainViewModel
import com.testapp.babylonplaceholder.presentation.app.snackBar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailedPostActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: DetailedPostViewModel

    private lateinit var binding: ActivityDetailedpostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = obtainViewModel(viewModelFactory, DetailedPostViewModel::class.java)
        viewModel.setPost(intent.getParcelableExtra(DATA))

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailedpost)
        binding.model = viewModel
        binding.lifecycleOwner = this

        viewModel.comments.observe(this, Observer {
            binding.list.scheduleLayoutAnimation()
        })

        viewModel.error.observe(this, Observer {
            snackBar(it)
        })
    }

    companion object {
        private const val DATA = "DATA_KEY"

        fun forPost(context: Context, post: Post): Intent {
            return Intent(context, DetailedPostActivity::class.java).apply {
                putExtra(DATA, post)
            }
        }
    }
}
