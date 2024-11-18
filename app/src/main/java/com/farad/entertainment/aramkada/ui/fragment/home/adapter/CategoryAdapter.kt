package com.farad.entertainment.aramkada.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.farad.entertainment.aramkada.R
import com.farad.entertainment.aramkada.base.BaseListAdapter
import com.farad.entertainment.aramkada.base.BaseViewHolder
import com.farad.entertainment.aramkada.data.model.CategoryModel
import com.farad.entertainment.aramkada.databinding.ItemCategoryBinding

class CategoryAdapter :
    BaseListAdapter<CategoryModel, CategoryAdapter.ViewHolderCategory>(DIFF_UTIL) {
    var selectLevelId = 1
    private var selectedPosition = -1

    private var onItemClickListener: ((CategoryModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (CategoryModel) -> Unit) {
        onItemClickListener = listener
    }

    private companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<CategoryModel>() {
            override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CategoryModel,
                newItem: CategoryModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class ViewHolderCategory(private val binding: ItemCategoryBinding) :
        BaseViewHolder<CategoryModel>(binding.root) {
        override fun bind(obj: CategoryModel) {
            binding.apply {
                itemView.isSelected = false
                binding.tvTitle.text = obj.title
                checkSelect(obj)
                binding.root.setOnClickListener {
                    selectLevelId = obj.id
                    selectLevel()
                    onItemClickListener?.invoke(obj)
                }
            }

        }

        private fun selectLevel() {
            if (selectedPosition >= 0)
                notifyItemChanged(selectedPosition)
            selectedPosition = adapterPosition
            notifyItemChanged(selectedPosition)
        }

        private fun checkSelect(obj: CategoryModel) {
            if (selectLevelId == obj.id) {
                selectedPosition = adapterPosition
                itemView.isSelected = true
                binding.tvTitle.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.colorActive
                    )
                )
            } else {
                binding.tvTitle.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.white
                    )
                )
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCategory {
        return ViewHolderCategory(
            ItemCategoryBinding.inflate(
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