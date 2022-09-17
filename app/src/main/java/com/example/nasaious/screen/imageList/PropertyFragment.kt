package com.example.nasaious.screen.imageList

import android.os.Bundle
import android.view.View
import com.example.nasaious.R
import com.example.nasaious.base.FragmentBase
import com.example.nasaious.databinding.FragmentPropertyBinding
import com.example.nasaious.screen.FacilityAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PropertyFragment : FragmentBase(R.layout.fragment_property) {

    @Inject
    lateinit var viewModel: PropertyViewModel

    private lateinit var facilityAdapter: FacilityAdapter

    private var _binding: FragmentPropertyBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        setBindings(view)
        setAdapter()
        setObservers()
        setEventHandlers()
    }

    private fun loadData() {
        viewModel.setPropertyId("iranjith4")
    }

    private fun setBindings(view: View) {
        _binding = FragmentPropertyBinding.bind(view)
        binding.property = viewModel.property
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setAdapter() {
        facilityAdapter = FacilityAdapter(requireContext())
        binding.facilitiesRecyclerView.adapter = facilityAdapter
    }

    private fun setObservers() {
        viewModel.property.observe {
            facilityAdapter.submitList(it.data?.facilities)
        }
    }

    private fun setEventHandlers() {
        binding.swipeFacilities.setOnRefreshListener {
            viewModel.retry()
            binding.swipeFacilities.isRefreshing = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}