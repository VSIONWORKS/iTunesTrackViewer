package com.example.itunestrackviewer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Our data class model
 * All possible fields from Api are set on this class
 * to get the values need by the object
 *
 * Added autogenerated primary key to put id on every item
 * since items does not have unique keys
 */
@Entity(tableName = "itemList")
data class Item(

    @SerializedName("wrapperType")
    val wrapperType: String = "",
    @SerializedName("kind")
    val kind: String = "",
    @SerializedName("artistId")
    val artistId: String = "",
    @SerializedName("collectionId")
    val collectionId: String = "",
    @SerializedName("trackId")
    val trackId: String = "",
    @SerializedName("artistName")
    val artistName: String = "",
    @SerializedName("collectionName")
    val collectionName: String = "",
    @SerializedName("trackName")
    val trackName: String = "",
    @SerializedName("collectionCensoredName")
    val collectionCensoredName: String = "",
    @SerializedName("trackCensoredName")
    val trackCensoredName: String = "",
    @SerializedName("artistViewUrl")
    val artistViewUrl: String = "",
    @SerializedName("collectionViewUrl")
    val collectionViewUrl: String = "",
    @SerializedName("trackViewUrl")
    val trackViewUrl: String = "",
    @SerializedName("previewUrl")
    val previewUrl: String = "",
    @SerializedName("artworkUrl30")
    val artworkUrl30: String = "",
    @SerializedName("artworkUrl60")
    val artworkUrl60: String = "",
    @SerializedName("artworkUrl100")
    val artworkUrl100: String = "",
    @SerializedName("collectionPrice")
    val collectionPrice: String = "",
    @SerializedName("trackPrice")
    val trackPrice: String = "",
    @SerializedName("releaseDate")
    val releaseDate: String = "",
    @SerializedName("collectionExplicitness")
    val collectionExplicitness: String = "",
    @SerializedName("trackExplicitness")
    val trackExplicitness: String = "",
    @SerializedName("discCount")
    val discCount: Int = 0,
    @SerializedName("discNumber")
    val discNumber: Int = 0,
    @SerializedName("trackCount")
    val trackCount: Int = 0,
    @SerializedName("trackNumber")
    val trackNumber: Int = 0,
    @SerializedName("trackTimeMillis")
    val trackTimeMillis: Int = 0,
    @SerializedName("country")
    val country: String = "",
    @SerializedName("currency")
    val currency: String = "",
    @SerializedName("primaryGenreName")
    val primaryGenreName: String = "",
    @SerializedName("isStreamable")
    val isStreamable: Boolean = false,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("shortDescription")
    val shortDescription: String = "",
    @SerializedName("longDescription")
    val longDescription: String = "",

    @PrimaryKey(autoGenerate = true)
    @SerializedName("itemId")
    var itemId: Int = 0,
    @SerializedName("viewed")
    val viewed: Boolean = false
) : Serializable