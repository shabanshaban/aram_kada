package com.farad.entertainment.aramkada.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.farad.entertainment.aramkada.R
import com.farad.entertainment.aramkada.base.BaseListAdapter
import com.farad.entertainment.aramkada.base.BaseViewHolder
import com.farad.entertainment.aramkada.data.model.CategoryItemModel
import com.farad.entertainment.aramkada.databinding.ItemCategoryItemBinding
import com.farad.entertainment.aramkada.utils.loadImage

class CategoryItemAdapter :
    BaseListAdapter<CategoryItemModel, CategoryItemAdapter.ViewHolderCategory>(DIFF_UTIL) {
    private var onItemClickListener: ((CategoryItemModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (CategoryItemModel) -> Unit) {
        onItemClickListener = listener
    }

    private companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<CategoryItemModel>() {
            override fun areItemsTheSame(
                oldItem: CategoryItemModel,
                newItem: CategoryItemModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CategoryItemModel,
                newItem: CategoryItemModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class ViewHolderCategory(private val binding: ItemCategoryItemBinding) :
        BaseViewHolder<CategoryItemModel>(binding.root) {
        override fun bind(obj: CategoryItemModel) {
            binding.apply {

                binding.cardViewItem.setOnClickListener {
                    onItemClickListener?.invoke(obj)
                }

                binding.imageItem.loadImage(obj.image)
                binding.tvTitle.text = obj.title
                binding.tvSessions.text =
                    binding.root.context.getString(R.string.sessions_s, obj.sessions)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCategory {
        return ViewHolderCategory(
            ItemCategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderCategory, position: Int) {
        holder.bind(getItem(position))
    }
}