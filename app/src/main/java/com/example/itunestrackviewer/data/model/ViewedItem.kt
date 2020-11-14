package com.example.itunestrackviewer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created this class to save the viewed item.
 * This will be use for the persistent mechanism
 */

@Entity(tableName = "viewedItems")
data class ViewedItem (

    @SerializedName("collectionId")
    val collectionId: String = "",
    @SerializedName("trackName")
    val trackName: String = "",
    @SerializedName("trackTimeMillis")
    val trackTimeMillis: Int = 0,
    @PrimaryKey
    @SerializedName("itemId")
    var itemId: Int = 0

)