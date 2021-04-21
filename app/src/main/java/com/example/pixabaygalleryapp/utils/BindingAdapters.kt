package com.example.pixabaygalleryapp.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pixabaygalleryapp.R


object BindingAdapters : BaseObservable() {

    @BindingAdapter("loadGroupImage")
    @JvmStatic
    fun loadGroupImage(imageView: AppCompatImageView, url: String?) {
        if (!url.isNullOrEmpty()) {

            val circleProgress = CircularProgressDrawable(imageView.context)
            circleProgress.strokeWidth = 5f
            circleProgress.centerRadius = 40f
            circleProgress.start()

            Glide.with(imageView.context)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .load(url)
                .placeholder(circleProgress)
                .error(R.drawable.loading)
                .into(imageView)
        }
    }


    @BindingAdapter("loadShortString")
    @JvmStatic
    fun loadShortString(txt: AppCompatTextView, name: String?) {
        txt.text = name?.shortStringLength()?.convertStringToUpperCase()
    }

}