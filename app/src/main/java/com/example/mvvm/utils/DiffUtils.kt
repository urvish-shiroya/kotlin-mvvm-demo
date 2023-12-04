package com.example.mvvm.utils

import com.example.mvvm.models.Countries

class DiffUtils {

    class CountriesDiffUtil() : androidx.recyclerview.widget.DiffUtil.ItemCallback<Countries>() {
        override fun areItemsTheSame(oldItem: Countries, newItem: Countries): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Countries, newItem: Countries): Boolean =
            oldItem.name?.official.toString() == newItem.name?.official.toString()
    }
}
