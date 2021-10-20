package com.example.pixabaygalleryapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


fun <T : ViewModel> Fragment.obtainViewModel(
    activity: FragmentActivity,
    viewModelClass: Class<T>,
    viewModelFactory: ViewModelProvider.Factory
) =
    ViewModelProvider(activity, viewModelFactory).get(viewModelClass)