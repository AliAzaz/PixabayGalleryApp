package com.example.pixabaygalleryapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabaygalleryapp.R
import com.example.pixabaygalleryapp.adapters.GenericListAdapter
import com.example.pixabaygalleryapp.base.FragmentBase
import com.example.pixabaygalleryapp.base.repository.ResponseStatus
import com.example.pixabaygalleryapp.base.viewmodel.ImageViewModel
import com.example.pixabaygalleryapp.databinding.FragmentImageListBinding
import com.example.pixabaygalleryapp.model.ImagesInfo
import com.example.pixabaygalleryapp.ui.MainActivity
import com.example.pixabaygalleryapp.utils.hideKeyboard
import com.example.pixabaygalleryapp.utils.obtainViewModel
import com.example.pixabaygalleryapp.utils.showSnackBar
import com.kennyc.view.MultiStateView
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext


class ImageListFragment : FragmentBase() {

    private val viewModel: ImageViewModel by lazy {
        obtainViewModel(requireActivity(), ImageViewModel::class.java, viewModelFactory)
    }
    private lateinit var adapter: GenericListAdapter<ImagesInfo>
    private lateinit var bi: FragmentImageListBinding
    private var actionBarHeight = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*
        * Initializing databinding
        * */
        return FragmentImageListBinding.inflate(inflater, container, false).apply {
            bi = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        /*
        * Get actionbar height for use in translation
        * */
        context?.let { item ->
            actionBarHeight = with(TypedValue().also {
                item.theme.resolveAttribute(
                    android.R.attr.actionBarSize,
                    it,
                    true
                )
            }) {
                TypedValue.complexToDimensionPixelSize(this.data, resources.displayMetrics)
            }

            /*
            * Translate items on menu click
            * */
            actionBarHeight *= -1
            bi.fldGrpSearchPhotos.translationY = actionBarHeight.toFloat()
            bi.nestedScrollView.translationY = actionBarHeight.toFloat() / 2

        }

        /*
        * Initiating recyclerview
        * */
        callingRecyclerView()

        /*
        * Fetch image list
        * */
        viewModel.imagesResponse.observe(viewLifecycleOwner, {
            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    it.data?.apply {
                        adapter.productItems = imagesInfo as ArrayList<ImagesInfo>
                        bi.multiStateView.viewState = MultiStateView.ViewState.CONTENT
                    }
                }
                ResponseStatus.ERROR -> {
                    it.data?.let { item ->
                        if (item.page == 1)
                            bi.multiStateView.viewState = MultiStateView.ViewState.EMPTY
                        else
                            bi.nestedScrollView.showSnackBar(
                                message = it.message.toString()
                            )
                    } ?: run {
                        bi.multiStateView.viewState = MultiStateView.ViewState.ERROR
                        bi.nestedScrollView.showSnackBar(
                            message = "Internet not available",
                            action = "Retry"
                        ) {
                            viewModel.retryConnection()
                        }

                    }
                }
                ResponseStatus.LOADING -> {
                    it.data?.let { item ->
                        if (item.page == 1) bi.multiStateView.viewState =
                            MultiStateView.ViewState.LOADING
                        else
                            bi.nestedScrollView.showSnackBar(
                                message = "Loading more images"
                            )
                    }
                }
            }

        })

        /*
        * Checking scrollview scroll end
        * */
        bi.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                viewModel.loadNextPagePhotos()
            }
        }

        /*
        * Image search
        * */
        bi.edtSearchPhotos.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                bi.edtSearchPhotos.hideKeyboard()
                val s = bi.edtSearchPhotos.text.toString()
                adapter.clearProductItem()
                bi.populateTxt.text = "Search: ${s.toUpperCase(Locale.ENGLISH)}"
                viewModel.searchImagesFromRemote(s)
            }
            false
        }

        /*
        * Image search clear
        * */
        bi.inputSearchPhotos.setEndIconOnClickListener {
            bi.edtSearchPhotos.setText("")
            adapter.clearProductItem()
            bi.populateTxt.text = "Search: Latest"
            viewModel.fetchImagesFromRemoteServer(1)
        }

    }

    /*
    * Initialize recyclerView with onClickListener
    * */
    @SuppressLint("ResourceType")
    private fun callingRecyclerView() {
        adapter = GenericListAdapter(R.layout.product_view) { item, position ->
            viewModel.setSelectedProduct(item)
            findNavController().navigate(ImageListFragmentDirections.actionImageListFragmentToImageDetailFragment())
        }
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        bi.productList.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.search_menu).isVisible = true
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search_menu -> {
                bi.fldGrpSearchPhotos.animate().apply {
                    duration = 1000
                    translationY(if (bi.fldGrpSearchPhotos.translationY == actionBarHeight.toFloat()) 10f else actionBarHeight.toFloat())
                }.start()
                bi.nestedScrollView.animate().apply {
                    duration = 1000
                    translationY(if (bi.nestedScrollView.translationY == actionBarHeight.toFloat() / 2) 10f else actionBarHeight.toFloat() / 2)
                }.start()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}