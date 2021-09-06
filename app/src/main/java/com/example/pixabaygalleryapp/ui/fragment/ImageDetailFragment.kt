package com.example.pixabaygalleryapp.ui.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.pixabaygalleryapp.R
import com.example.pixabaygalleryapp.base.FragmentBase
import com.example.pixabaygalleryapp.base.repository.ResponseStatus
import com.example.pixabaygalleryapp.base.viewmodel.ImageViewModel
import com.example.pixabaygalleryapp.databinding.FragmentImageDetailBinding
import com.example.pixabaygalleryapp.model.ImagesInfo
import com.example.pixabaygalleryapp.ui.MainActivity
import com.example.pixabaygalleryapp.utils.obtainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ImageDetailFragment : FragmentBase() {

    lateinit var viewModel: ImageViewModel
    lateinit var bi: FragmentImageDetailBinding
    var data: ImagesInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.search_menu).isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*
        * Initializing databinding
        * */
        return FragmentImageDetailBinding.inflate(inflater, container, false).apply {
            bi = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        * Obtaining ViewModel
        * */
        viewModel = obtainViewModel(
            activity as MainActivity,
            ImageViewModel::class.java,
            viewModelFactory
        )

        /*
        * Fetch product list
        * */
        viewModel.selectedImagesResponse.observe(viewLifecycleOwner, {
            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    data = it.data
                    data?.let { item ->

                        /*
                        * Passing data to view
                        * */
                        bi.setVariable(BR.selImageItem, item)

                        /*
                        * Show loading and wait until view is not ready
                        * */
                        lifecycleScope.launch {
                            delay(500)
                            bi.productImg.visibility = View.VISIBLE
                        }
                    }
                }
                ResponseStatus.ERROR -> {
                }
                ResponseStatus.LOADING -> {
                }
            }

        })
    }

}