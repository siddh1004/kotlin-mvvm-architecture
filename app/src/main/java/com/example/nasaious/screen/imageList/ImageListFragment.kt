package com.example.nasaious.screen.imageList

import android.os.Bundle
import android.view.View
import com.example.nasaious.R
import com.example.nasaious.base.FragmentBase
import com.example.nasaious.databinding.FragmentImageListBinding
import com.example.nasaious.domain.model.Image
import com.example.nasaious.screen.FacilityAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImageListFragment : FragmentBase(R.layout.fragment_image_list) {

    @Inject
    lateinit var viewModel: PropertyViewModel

    private lateinit var facilityAdapter: FacilityAdapter

    private var _binding: FragmentImageListBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        setBindings(view)
        setAdapter()
//        setObservers()
    }

    private fun loadData() {
        viewModel.setPropertyId("iranjith4")
    }

    private fun setBindings(view: View) {
        _binding = FragmentImageListBinding.bind(view)
//        binding.property = viewModel.property
    }

    private fun setAdapter() {
        facilityAdapter = FacilityAdapter()
        binding.facilitiesRecyclerView.adapter = facilityAdapter
    }

//    private fun setObservers() {
//        viewModel.viewState.observe { viewState ->
//            binding.progress.isVisible = viewState is Loading
//            when (viewState) {
//                is Success -> {
//                    val test = viewState.data
//                    val test2 = test
////                    optionAdapter.submitList(viewState.data)
//                }
//                else -> {
//                }
//            }
//        }
//    }

    private fun onImageClick(image: Image) {
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}