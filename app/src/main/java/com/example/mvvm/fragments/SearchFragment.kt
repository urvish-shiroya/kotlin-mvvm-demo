package com.example.mvvm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.base.BaseFragment
import com.example.mvvm.databinding.FragmentSearchBinding
import com.example.mvvm.viewmodel.SearchFragmentViewModel


class SearchFragment : BaseFragment() {

    private val binding: FragmentSearchBinding by lazy {
        FragmentSearchBinding.inflate(layoutInflater)
    }

    private val viewModel: SearchFragmentViewModel by lazy {
        ViewModelProvider(this)[SearchFragmentViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            tvText.text = viewModel.fetchApiIfNeeded()
        }
    }
}