package com.example.itunestrackviewer.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.itunestrackviewer.data.local.ItemService
import com.example.itunestrackviewer.data.model.ViewedItem

/**
 * View-model of ItemDetailActivity
 */
class DetailViewModel(val context : Context): ViewModel() {

    fun saveItemAsViewed(item: ViewedItem) {
        ItemService.saveViewedItem(context,item)
    }
}