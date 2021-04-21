package com.example.pixabaygalleryapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabaygalleryapp.model.ImagesInfo
import com.example.pixabaygalleryapp.viewholder.ImageViewHolder
import kotlinx.android.synthetic.main.product_view.view.*

/**
 * @author AliAzazAlam on 4/20/2021.
 */
class ImageListAdapter(private val clickListener: OnItemClickListener) : RecyclerView.Adapter<ImageViewHolder>() {

    var productItems: ArrayList<ImagesInfo> = ArrayList()
        set(value) {
            field = value
            val diffCallback = ImageViewHolder.ChildViewDiffUtils(filteredProductItems, productItems)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            if (filteredProductItems.size > 0)
                filteredProductItems.clear()
            filteredProductItems.addAll(value)
            diffResult.dispatchUpdatesTo(this)
        }

    private var filteredProductItems: ArrayList<ImagesInfo> = ArrayList()
        set(value) {
            field = value
            val diffCallback = ImageViewHolder.ChildViewDiffUtils(filteredProductItems, productItems)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            diffResult.dispatchUpdatesTo(this)
        }

    fun clearProductItem(){
        filteredProductItems.clear()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ImageViewHolder {
        return ImageViewHolder.create(viewGroup)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, i: Int) {
        val item = filteredProductItems[i]
        holder.bind(item)
        holder.itemView.parentLayout.setOnClickListener {
            clickListener.onItemClick(item, i)
        }
    }

    override fun getItemCount(): Int = filteredProductItems.size

    interface OnItemClickListener {
        fun onItemClick(item: ImagesInfo, position: Int)
    }
}