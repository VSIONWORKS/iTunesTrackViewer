package com.example.itunestrackviewer.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.itunestrackviewer.data.remote.ApiService
import com.example.itunestrackviewer.data.repository.Repository
/**
 * Factory Class pass to ViewModelProvider as parameter value
 */
class ViewModelFactory( private val context: Context,
    private val apiService: ApiService): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(context,Repository(apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
