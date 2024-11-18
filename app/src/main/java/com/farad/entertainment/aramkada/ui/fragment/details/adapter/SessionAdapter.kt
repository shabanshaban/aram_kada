package com.farad.entertainment.aramkada.ui.fragment.details.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.farad.entertainment.aramkada.R
import com.farad.entertainment.aramkada.base.BaseListAdapter
import com.farad.entertainment.aramkada.base.BaseViewHolder
import com.farad.entertainment.aramkada.data.model.SessionModel
import com.farad.entertainment.aramkada.databinding.ItemSessionBinding
import com.farad.entertainment.aramkada.utils.getColorCompat
import com.farad.entertainment.aramkada.utils.loadImage

class SessionAdapter :
    BaseListAdapter<SessionModel, SessionAdapter.ViewHolderCategory>(DIFF_UTIL) {



    private companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<SessionModel>() {
            override fun areItemsTheSame(oldItem: SessionModel, newItem: SessionModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: SessionModel,
                newItem: SessionModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class ViewHolderCategory(private val binding: ItemSessionBinding) :
        BaseViewHolder<SessionModel>(binding.root) {
        override fun bind(obj: SessionModel) {
            binding.apply {
                root.context.apply {
                    if (obj.free == 0) {
                        imageFree.loadImage(R.drawable.ic_lock)
                        cardViewSession.setStrokeColor(ColorStateList.valueOf(getColorCompat(R.color.colorActive)))
                        cardViewSession.setCardBackgroundColor(Color.WHITE)

                    } else {
                        imageFree.loadImage(R.drawable.ic_tick)
                        cardViewSession.setStrokeColor(ColorStateList.valueOf(Color.TRANSPARENT))
                        cardViewSession.setCardBackgroundColor(getColorCompat(R.color.back_cardview))
                    }
                    itemView.isSelected = false
                    tvTitle.text = obj.session
                    tvTime.text = getString(R.string.minute_s, obj.time)
                    root.setOnClickListener {


                    }
                }
            }


        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCategory {
        return ViewHolderCategory(
            ItemSessionBinding.inflate(
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