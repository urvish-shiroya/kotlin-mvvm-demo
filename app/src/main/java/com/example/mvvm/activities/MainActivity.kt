package com.example.mvvm.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.R
import com.example.mvvm.base.BaseActivity
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.fragments.HomeFragment
import com.example.mvvm.fragments.SearchFragment
import com.example.mvvm.utils.extentions.getCompactColor
import com.example.mvvm.viewmodel.MainActivityViewModel
import com.google.android.material.navigation.NavigationBarView

class MainActivity : BaseActivity(), NavigationBarView.OnItemSelectedListener {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = getCompactColor(R.color.background_color)

        initializeView()
        initializeClickListener()
    }

    private fun initializeView() {
        binding.apply {

        }
    }

    private fun initializeClickListener() {
        binding.apply {
            bottomNavigationBar.setOnItemSelectedListener(this@MainActivity)
        }
    }

    override fun onStart() {
        super.onStart()
        loadFragment(HomeFragment())
    }

    private fun loadFragment(fragment: Fragment?) {
        fragment?.let {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, it as Fragment)
                .commit()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_dashboard -> {
                loadFragment(HomeFragment())
            }

            R.id.menu_search -> {
                loadFragment(SearchFragment())
            }

            else -> {
                return false
            }
        }
        return true
    }
}