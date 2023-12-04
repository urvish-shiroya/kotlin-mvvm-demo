package com.example.mvvm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm.base.BaseFragment
import com.example.mvvm.databinding.FragmentHomeBinding
import com.example.mvvm.databinding.ItemCountriesBinding
import com.example.mvvm.models.Countries
import com.example.mvvm.network.NetworkResponse
import com.example.mvvm.utils.DiffUtils
import com.example.mvvm.utils.Store
import com.example.mvvm.utils.extentions.gone
import com.example.mvvm.utils.extentions.isExpired
import com.example.mvvm.utils.extentions.showToast
import com.example.mvvm.utils.extentions.visible
import com.example.mvvm.viewmodel.HomeFragmentViewModel
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment() {

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val viewModel: HomeFragmentViewModel by lazy {
        ViewModelProvider(this)[HomeFragmentViewModel::class.java]
    }

    private val manager: LinearLayoutManager by lazy {
        LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    private val adapter: CountriesAdapter by lazy {
        CountriesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
        initializeObserver()
    }

    private fun initializeViews() {

        binding.apply {
            recyclerView.layoutManager = manager
            recyclerView.adapter = adapter
        }

        if (Store.expiredTime?.isExpired() == true) {
            viewModel.getAllCountries()
        } else {
            viewModel.getListFromCache()
        }
    }

    private fun initializeObserver() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.allCountriesAsSharedFlow.collect { networkResponse ->
                    when (networkResponse) {
                        is NetworkResponse.Default -> {}
                        is NetworkResponse.Failure -> {
                            binding.progressBar.gone()
                            requireContext().showToast(networkResponse.error.toString())
                        }

                        is NetworkResponse.Loading -> {
                            binding.progressBar.visible()
                        }

                        is NetworkResponse.Success -> {
                            binding.progressBar.gone()
                            adapter.submitList(networkResponse.data?.sortedBy {
                                it.name?.common
                            })
                        }
                    }
                }
            }
        }
    }

    inner class CountriesAdapter() :
        ListAdapter<Countries, CountriesAdapter.ViewHolder>(DiffUtils.CountriesDiffUtil()) {

        inner class ViewHolder(val adapterBinding: ItemCountriesBinding) :
            RecyclerView.ViewHolder(adapterBinding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(ItemCountriesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val mData = currentList[position]

            holder.apply {
                adapterBinding.apply {
                    tvText.text = mData?.name?.common.toString()
                    tvOfficialName.text = mData?.name?.official.toString()

                    Glide.with(requireContext()).load(
                        mData?.flags?.png.toString()
                    ).into(circleImageView)
                }
            }
        }

    }

}