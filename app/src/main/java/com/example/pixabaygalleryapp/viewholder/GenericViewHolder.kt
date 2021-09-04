package com.example.pixabaygalleryapp.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.library.baseAdapters.BR

/**
 * @author AliAzazAlam on 4/20/2021.
 */
class GenericViewHolder<T>(private val bi: ViewDataBinding) :
    RecyclerView.ViewHolder(bi.root) {

    fun bind(item: T) {
        bi.apply {
            bi.setVariable(BR.item, item)
            bi.executePendingBindings()
        }
    }

    class ChildViewDiffUtils<T>(
        private val oldList: ArrayList<T>,
        private val newList: ArrayList<T>
    ) :
        DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].toString() == newList[newItemPosition].toString()
        }

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}