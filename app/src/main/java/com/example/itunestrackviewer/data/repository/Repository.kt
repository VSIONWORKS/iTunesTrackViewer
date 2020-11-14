package com.example.itunestrackviewer.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.itunestrackviewer.data.local.ItemService
import com.example.itunestrackviewer.data.model.ApiData
import com.example.itunestrackviewer.data.model.Item
import com.example.itunestrackviewer.data.remote.ApiService
import io.reactivex.Single

/**
 * Main Repository where the view-model connects
 * to get the clean data from Room database and web services
 */
class Repository(private val apiService: ApiService) {

    fun getItems(): Single<ApiData> {
        return apiService.getItems()
    }
    fun getPlayList(context : Context): LiveData<List<Item>> {
        return ItemService.getPlayList(context)
    }
    fun getFeatured(context : Context): LiveData<List<Item>> {
        return ItemService.getFeatured(context)
    }
    fun loadItems(context : Context, items : List<Item>){
        ItemService.loadItems(context,items)
    }


}