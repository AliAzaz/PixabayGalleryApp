package com.example.pixabaygalleryapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.activityViewModels
import com.example.pixabaygalleryapp.R
import com.example.pixabaygalleryapp.base.FragmentBase
import com.example.pixabaygalleryapp.databinding.FragmentImageDetailBinding
import com.example.pixabaygalleryapp.di.repository.ResponseStatus
import com.example.pixabaygalleryapp.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailFragment : FragmentBase() {

    private val viewModel: ImageViewModel by activityViewModels()
    private lateinit var bi: FragmentImageDetailBinding

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
        * Fetch product list
        * */
        viewModel.selectedImagesResponse.observe(viewLifecycleOwner) {
            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    it.data?.let { item ->
                        //Passing data to view
                        bi.setVariable(BR.selImageItem, item)
                    }
                }
                ResponseStatus.ERROR -> {
                }
                ResponseStatus.LOADING -> {
                }
            }

        }
    }

}