package com.example.pixabaygalleryapp.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.lifecycleScope
import com.example.pixabaygalleryapp.R
import com.example.pixabaygalleryapp.base.FragmentBase
import com.example.pixabaygalleryapp.base.repository.ResponseStatus
import com.example.pixabaygalleryapp.base.viewmodel.ImageViewModel
import com.example.pixabaygalleryapp.databinding.FragmentImageDetailBinding
import com.example.pixabaygalleryapp.ui.MainActivity
import com.example.pixabaygalleryapp.utils.obtainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ImageDetailFragment : FragmentBase() {

    lateinit var viewModel: ImageViewModel
    lateinit var bi: FragmentImageDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false);
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
        bi = FragmentImageDetailBinding.inflate(inflater, container, false)
        return bi.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
                    val data = it.data
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