package com.example.pixabaygalleryapp.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabaygalleryapp.R
import com.example.pixabaygalleryapp.databinding.ProductViewBinding
import androidx.databinding.library.baseAdapters.BR
import com.example.pixabaygalleryapp.model.ImagesInfo

/**
 * @author AliAzazAlam on 4/20/2021.
 */
class ImageViewHolder(private val bi: ProductViewBinding) :
    RecyclerView.ViewHolder(bi.root) {

    fun bind(item: ImagesInfo) {
        bi.apply {
            bi.setVariable(BR.imageItem, item)
            bi.executePendingBindings()
        }
    }


    companion object {
        fun create(viewGroup: ViewGroup): ImageViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.product_view, viewGroup, false)
            val binding = ProductViewBinding.bind(view)
            return ImageViewHolder(binding)
        }
    }

    class ChildViewDiffUtils(
        private val oldList: ArrayList<ImagesInfo>,
        private val newList: ArrayList<ImagesInfo>
    ) :
        DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
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