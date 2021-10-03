package com.sample.showroomtest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sample.showroomtest.databinding.FragmentRepositoryListBinding
import java.util.*
import kotlin.concurrent.schedule


const val FRAGMENT = "RepositoryListFragment"

class RepositoryListFragment : Fragment() {

    val viewModel: ViewModel by lazy {
        ViewModelProvider(this).get(ViewModel::class.java)
    }
    private lateinit var binding: FragmentRepositoryListBinding
    private val repositoryAdapter: RepositoryAdapter = RepositoryAdapter()

    private var timer: Timer = Timer()
    private val delay: Long = 500

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repository_list, container, false)
        binding.apply {
            repositoryList.adapter = repositoryAdapter
            isLoading = true
        }
        setupUi()
        binding.search.isSubmitButtonEnabled = true
        viewModel.resultList.observeForever{ result ->
            result.onSuccess { response ->
                repositoryAdapter.setRepositoryList(response.items)
            }

        }
        return binding.root
    }

    private fun setupUi() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // キーボードで決定or検索ボタンを押下したときに検索開始
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    // NetworkOnMainThreadException回避
                    timer.schedule(delay) {
                        viewModel.searchRepository(query)
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }
}
