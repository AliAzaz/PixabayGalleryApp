package com.example.pixabaygalleryapp.utils.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabaygalleryapp.utils.adapters.viewholder.GenericViewHolder

/**
 * @author AliAzazAlam on 4/20/2021.
 * @updated AliAzazAlam on 09/03/2021
 */
class GenericListAdapter<T> internal constructor(
    @IdRes private val layout: Int,
    private val clickListener: (item: T, position: Int) -> Unit
) : RecyclerView.Adapter<GenericViewHolder<T>>() {

    var productItems: ArrayList<T> = ArrayList()
        set(value) {
            field = value
            val diffCallback =
                GenericViewHolder.ChildViewDiffUtils(filteredProductItems, productItems)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            diffResult.dispatchUpdatesTo(this)
            if (filteredProductItems.size > 0)
                filteredProductItems.clear()
            filteredProductItems.addAll(value)
        }

    private var filteredProductItems: ArrayList<T> = ArrayList()
        set(value) {
            field = value
            val diffCallback =
                GenericViewHolder.ChildViewDiffUtils(filteredProductItems, productItems)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            diffResult.dispatchUpdatesTo(this)
        }

    fun clearProductItem() {
        productItems.clear()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GenericViewHolder<T> {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, i, viewGroup, false)
        return GenericViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<T>, i: Int) {
        val item = filteredProductItems[i]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            clickListener(item, i)
        }
    }

    override fun getItemCount(): Int = filteredProductItems.size

    override fun getItemViewType(position: Int) = layout

}