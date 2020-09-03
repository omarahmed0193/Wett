package com.afterapps.wett.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.afterapps.wett.databinding.FragmentHomeWeatherBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeWeatherFragment : Fragment() {
    private val viewModel: HomeWeatherViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeWeatherBinding.inflate(inflater)

        initViews(binding)

        return binding.root
    }

    private fun initViews(binding: FragmentHomeWeatherBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }
}