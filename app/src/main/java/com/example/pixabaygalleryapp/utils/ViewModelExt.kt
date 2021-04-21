package com.example.pixabaygalleryapp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


fun <T : ViewModel> Fragment.obtainViewModel(
    activity: AppCompatActivity,
    viewModelClass: Class<T>,
    viewModelFactory: ViewModelProvider.Factory
) =
    ViewModelProvider(activity, viewModelFactory).get(viewModelClass)