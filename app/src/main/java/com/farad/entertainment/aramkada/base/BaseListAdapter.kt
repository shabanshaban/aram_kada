package com.farad.entertainment.aramkada.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


abstract class BaseListAdapter<T, VH : RecyclerView.ViewHolder?>(diffUtil: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, VH>(diffUtil) {

    override fun submitList(list: List<T>?) {
        super.submitList(list?.let { ArrayList(it) })
    }


}


