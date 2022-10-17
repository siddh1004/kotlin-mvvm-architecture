package com.example.nasaious.screen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.nasaious.R
import com.example.nasaious.base.FragmentBase
import com.example.nasaious.databinding.FragmentNewsDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsDetailFragment : FragmentBase(R.layout.fragment_news_detail) {

    @Inject
    lateinit var newsViewModel: NewsViewModel

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val args: NewsDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        setBindings(view)
        setEventHandlers()
        setObservers()
    }

    private fun loadData() {
        newsViewModel.setTitle(args.title)
    }

    private fun setBindings(view: View) {
        _binding = FragmentNewsDetailBinding.bind(view)
    }

    private fun setEventHandlers() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setObservers() {
        newsViewModel.newsDetail.observe { data ->
            binding.article = data
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}