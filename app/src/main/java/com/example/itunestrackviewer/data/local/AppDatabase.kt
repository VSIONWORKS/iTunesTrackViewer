package com.example.itunestrackviewer.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.itunestrackviewer.data.model.Item
import com.example.itunestrackviewer.data.model.ViewedItem

@Database( entities = [Item::class,ViewedItem::class], version = 1 )
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also {
                instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "iTunesItemDatabase")
                .fallbackToDestructiveMigration()
                .build()
    }

}