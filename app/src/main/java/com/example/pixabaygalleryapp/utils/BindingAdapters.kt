package com.example.pixabaygalleryapp.utils

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pixabaygalleryapp.R
import com.github.chrisbanes.photoview.PhotoView


object BindingAdapters : BaseObservable() {

    @BindingAdapter("loadGroupImage")
    @JvmStatic
    fun loadGroupImage(imageView: AppCompatImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            bindImageToImageView(imageView, url)
        }
    }

    @BindingAdapter("loadChrisImage")
    @JvmStatic
    fun loadChrisImage(imageView: PhotoView, url: String?) {
        if (!url.isNullOrEmpty()) {
            bindImageToImageView(imageView, url)
        }
    }

    private fun bindImageToImageView(imageView: ImageView, url: String?) {
        val circleProgress = CircularProgressDrawable(imageView.context)
        circleProgress.apply {
            strokeWidth = 5f
            centerRadius = 40f
            start()
        }

        Glide.with(imageView.context)
            .asBitmap()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .load(url)
            .placeholder(circleProgress)
            .error(R.drawable.loading)
            .into(imageView)
    }


    @BindingAdapter("loadShortString")
    @JvmStatic
    fun loadShortString(txt: AppCompatTextView, name: String?) {
        txt.text = name?.shortStringLength()?.convertStringToUpperCase()
    }

}