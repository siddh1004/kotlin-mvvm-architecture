package com.example.nasaious.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.nasaious.R

abstract class FragmentBase(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    private val navOptionsBuilder =
        NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in)
            .setExitAnim(R.anim.fade_out)
            .setPopEnterAnim(R.anim.fade_in)
            .setPopExitAnim(R.anim.slide_out)

    fun navigateTo(direction: NavDirections) {
        val navAction =
            requireNotNull(findNavController().currentDestination).getAction(direction.actionId)
        val predefinedNavOptions = requireNotNull(navAction).navOptions

        predefinedNavOptions?.apply {
            navOptionsBuilder.setPopUpTo(popUpToId, isPopUpToInclusive())
        }

        findNavController().navigate(direction, navOptionsBuilder.build())
    }

    protected fun <T> LiveData<T>.observe(observer: Observer<T>) =
        observe(viewLifecycleOwner, observer)
}