package com.example.pixabaygalleryapp.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.pixabaygalleryapp.R
import com.example.pixabaygalleryapp.base.FragmentBase
import com.example.pixabaygalleryapp.di.repository.ResponseStatus
import com.example.pixabaygalleryapp.viewmodel.ImageViewModel
import com.example.pixabaygalleryapp.databinding.FragmentImageDetailBinding
import com.example.pixabaygalleryapp.model.ImagesInfo
import com.example.pixabaygalleryapp.utils.launchItemInScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ImageDetailFragment : FragmentBase() {

    private val viewModel: ImageViewModel by activityViewModels()
    private lateinit var bi: FragmentImageDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*
        * Initializing databinding and setting viewmodel
        * */
        return FragmentImageDetailBinding.inflate(inflater, container, false).apply {
            bi = this
            imageViewModel = viewModel
        }.root
    }

}