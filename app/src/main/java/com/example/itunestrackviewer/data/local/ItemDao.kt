package com.example.itunestrackviewer.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.itunestrackviewer.data.model.Item
import com.example.itunestrackviewer.data.model.ViewedItem


@Dao
interface ItemDao {

    @Query("SELECT * FROM itemList where kind != 'feature-movie'")
    fun getPlayList() : LiveData<List<Item>>

    @Query("SELECT * FROM itemList where kind = 'feature-movie'")
    fun getFeatured() : LiveData<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<Item>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertViewedItem(viewedItem: ViewedItem)

    @Query("Update itemList set viewed = 1 where itemId =:id")
    suspend fun updateItemList(id:Int)


}