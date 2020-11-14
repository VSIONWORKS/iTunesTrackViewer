package com.example.itunestrackviewer.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.itunestrackviewer.data.model.Item
import com.example.itunestrackviewer.data.model.ViewedItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Local Service that runs
 * and provide all queries.
 */
class ItemService {

    companion object {

        var appDB: AppDatabase? = null

        private fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDatabase( context )
        }

        fun getPlayList(context: Context) : LiveData<List<Item>> {
            appDB = initializeDB(context)
            return appDB!!.itemDao().getPlayList()
        }

        fun getFeatured(context: Context) : LiveData<List<Item>> {
            appDB = initializeDB(context)
            return appDB!!.itemDao().getFeatured()
        }

        fun saveViewedItem(context: Context, viewedItem: ViewedItem) {
            appDB = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                appDB!!.itemDao().insertViewedItem(viewedItem)
                appDB!!.itemDao().updateItemList(viewedItem.itemId)
            }
        }

        fun loadItems(context: Context, items : List<Item> ) {
            appDB = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                appDB!!.itemDao().insertItems(items)
            }
        }

    }
}