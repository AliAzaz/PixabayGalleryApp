package com.example.pixabaygalleryapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabaygalleryapp.R
import com.example.pixabaygalleryapp.adapters.GenericListAdapter
import com.example.pixabaygalleryapp.base.FragmentBase
import com.example.pixabaygalleryapp.viewmodel.ImageViewModel
import com.example.pixabaygalleryapp.databinding.FragmentImageListBinding
import com.example.pixabaygalleryapp.model.ImagesInfo
import com.example.pixabaygalleryapp.utils.*
import org.apache.commons.lang3.StringUtils
import java.util.*


class ImageListFragment : FragmentBase() {

    private val viewModel: ImageViewModel by activityViewModels()
    private lateinit var adapter: GenericListAdapter<ImagesInfo>
    private lateinit var bi: FragmentImageListBinding
    private var actionBarHeight = 0

    private var pagination = 1
    private var searchImage = StringUtils.EMPTY

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

        settingRecyclerView()

        observingItems()

        bi.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                viewModel.loadNextPagePhotos(++pagination, adapter.productItems, searchImage)
            }
        }

        /*
        * Image search
        * */
        bi.edtSearchPhotos.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val s = bi.edtSearchPhotos.text.toString()
                bi.edtSearchPhotos.hideKeyboard()
                adapter.clearProductItem()
                pagination = 1
                searchImage = s
                bi.populateTxt.text = "Search: ${s.toUpperCase(Locale.ENGLISH)}"
                viewModel.fetchSearchImagesFromRemoteServer(pagination, arrayListOf(), s)
            }
            false
        }

        /*
        * Image search clear
        * */
        bi.inputSearchPhotos.setEndIconOnClickListener {
            bi.edtSearchPhotos.setText(StringUtils.EMPTY)
            adapter.clearProductItem()
            bi.edtSearchPhotos.hideKeyboard()
            pagination = 1
            searchImage = StringUtils.EMPTY
            bi.populateTxt.text = "Search: Latest"
            viewModel.fetchImagesFromRemoteServer(1)
        }

    }

    private fun observingItems() {
        launchItemInScope {
            viewModel.showSuccessSnackBar().collect {
                bi.nestedScrollView.showSnackBar(message = it)
            }
        }

        launchItemInScope {
            viewModel.showErrorSnackBar().collect {
                bi.nestedScrollView.showSnackBar(message = it, action = "Retry") {
                    viewModel.retryConnection(pagination, adapter.productItems, searchImage)
                }
            }
        }

        viewModel.imagesResult().observe(viewLifecycleOwner) {
            adapter.productItems = it as ArrayList<ImagesInfo>
            bi.productList.visible()
        }
    }

    /*
    * Initialize recyclerView with onClickListener
    * */
    @SuppressLint("ResourceType")
    private fun settingRecyclerView() {
        adapter = GenericListAdapter(R.layout.product_view) { item, position ->
            viewModel.setSelectedProduct(item).let {
                findNavController().navigate(ImageListFragmentDirections.actionImageListFragmentToImageDetailFragment())
            }
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