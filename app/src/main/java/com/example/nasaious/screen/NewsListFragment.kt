package com.example.nasaious.screen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nasaious.R
import com.example.nasaious.base.FragmentBase
import com.example.nasaious.databinding.FragmentNewsListBinding
import com.example.nasaious.domain.model.Article
import com.example.nasaious.domain.model.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsListFragment : FragmentBase(R.layout.fragment_news_list) {

    @Inject
    lateinit var newsListViewModel: NewsViewModel

    private lateinit var imageItemAdapter: ImageItemAdapter

    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        setBindings(view)
        setAdapter()
        setObservers()
    }

    private fun loadData() {
        newsListViewModel.setCountry("in")
    }

    private fun setBindings(view: View) {
        _binding = FragmentNewsListBinding.bind(view)
    }

    private fun setAdapter() {
        imageItemAdapter = ImageItemAdapter(::onNewsClick)
        binding.imageRecyclerView.adapter = imageItemAdapter
        binding.imageRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun setObservers() {
        newsListViewModel.property.observe { resource ->
            binding.progress.isVisible = resource.status == Status.LOADING
            when (resource.status) {
                Status.SUCCESS -> {
                    imageItemAdapter.submitList(resource.data)
                }
                else -> {
                }
            }
        }
    }

    private fun onNewsClick(article: Article) {
        navigateTo(NewsListFragmentDirections.showNewsDetail(article.title))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}