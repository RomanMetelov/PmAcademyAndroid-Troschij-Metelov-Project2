package com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.pmacademyandroid_troschij_metelov_project2.R
import com.example.pmacademyandroid_troschij_metelov_project2.databinding.ActivityUsersSearchBinding

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

class UsersSearchActivity : AppCompatActivity() {
    private val binding: ActivityUsersSearchBinding by lazy {
        ActivityUsersSearchBinding.inflate(layoutInflater)
    }
    private val searchAdapter = GitHubUsersAdapter()

    private val viewModel: SearchViewModel by viewModels()

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.searchResult.adapter = searchAdapter
        viewModel.searchResult.observe(this) { handleSearchResult(it) }

        // Start with empty query view
        searchAdapter.submitList(emptyList())
        binding.otherResultText.visibility = View.VISIBLE
        binding.searchResult.visibility = View.GONE
        binding.otherResultText.setText(R.string.not_enough_characters)
        binding.searchText.requestFocus()

        binding.searchText.doAfterTextChanged { editable ->
            lifecycleScope.launch {
                viewModel.queryChannel.send(editable.toString())
            }
        }
    }

    private fun handleSearchResult(it: SearchResult) {
        when (it) {
            is ValidResult -> {
                binding.otherResultText.visibility = View.GONE
                binding.searchResult.visibility = View.VISIBLE
                searchAdapter.submitList(it.result)
            }
            is ErrorResult -> {
                searchAdapter.submitList(emptyList())
                binding.otherResultText.visibility = View.VISIBLE
                binding.searchResult.visibility = View.GONE
                binding.otherResultText.setText(R.string.search_error)
            }
            is EmptyResult -> {
                searchAdapter.submitList(emptyList())
                binding.otherResultText.visibility = View.VISIBLE
                binding.searchResult.visibility = View.GONE
                binding.otherResultText.setText(R.string.empty_result)
            }
            is EmptyQuery -> {
                searchAdapter.submitList(emptyList())
                binding.otherResultText.visibility = View.VISIBLE
                binding.searchResult.visibility = View.GONE
                binding.otherResultText.setText(R.string.not_enough_characters)
            }
            is TerminalError -> {
                // Something wen't terribly wrong!
                println("Our Flow terminated unexpectedly, so we're bailing!")
                Toast.makeText(
                        this,
                        "Unexpected error in SearchRepository!",
                        Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }
}