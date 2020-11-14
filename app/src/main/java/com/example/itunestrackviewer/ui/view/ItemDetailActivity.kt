package com.example.itunestrackviewer.ui.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.itunestrackviewer.R
import com.example.itunestrackviewer.data.model.Item
import com.example.itunestrackviewer.data.model.ViewedItem
import com.example.itunestrackviewer.viewmodel.DetailViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_item_detail.*

class ItemDetailActivity : AppCompatActivity() {

    private lateinit var preferences : SharedPreferences
    private val prefLastViewed = "LAST_VIEWED"

    lateinit var item : Item
    private lateinit var itemDetailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        preferences = getSharedPreferences(prefLastViewed, Context.MODE_PRIVATE)

        // initialize viewModel
        initViewModel()

        /**
         * Check passed object from Home Activity
         * Or loads data from shared preference for persistent mechanism.
         */
        try {
            item = (intent.getSerializableExtra("ITEM_OBJECT") as Item?)!!
            // load to ui
            loadDetails(item)
        }
        catch (e: Exception) {
            loadFromSharedPref()
            e.printStackTrace()
        }
    }

    private fun  initViewModel(){
        itemDetailViewModel = DetailViewModel( this)
    }

    /**
     * Get the object to display values to ui
     */
    private fun loadDetails(item:Item) {

        if ( item.kind == "feature-movie" ) {
            setImageView(true)
        }
        else {
            setImageView(false)
        }

        if ( item.trackName.isNotEmpty() ) {
            itemViewName.text = item.trackName
        }
        else {
            itemViewName.text = item.collectionName
        }

        itemViewArtist.text = item.artistName
        itemViewGenre.text = item.primaryGenreName

        if ( item.trackPrice.isNotEmpty() ) {
            itemViewPrice.text = item.currency + " " + item.trackPrice
        }
        else {
            itemViewPrice.text = item.currency + " " + item.collectionPrice
        }

        var description = ""
        description = if ( item.longDescription.isNotEmpty() ) {
            item.longDescription
        } else if ( item.description.isNotEmpty() ) {
            item.description
        }
        else {
            "No available description"
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            itemViewDescription.text =
                Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT)
        } else {
            itemViewDescription.text = Html.fromHtml(description)
        }

        saveViewedItem()

    }

    /**
     * Set if the image is Large or small
     */
    private fun setImageView(isLarge:Boolean) {

        if(isLarge){
            Glide.with(this).load(item!!.artworkUrl100)
                .into(itemViewArtworkL)
            itemViewArtworkL.visibility = View.VISIBLE
        }
        else{
            Glide.with(this).load(item!!.artworkUrl100)
                .into(itemViewArtworkS)
            itemViewArtworkS.visibility = View.VISIBLE
        }
        ItemArtwork.visibility = View.VISIBLE
    }

    /**
     * Save current viewed item to local database
     */
    private fun saveViewedItem() {
        val viewedItem = ViewedItem(item.collectionId,item.trackName,item.trackTimeMillis,item.itemId)
        itemDetailViewModel.saveItemAsViewed(viewedItem)

        updateSharedPref(false)
    }

    /**
     * Load the previous viewed item from shared preferences
     * Used Gson to format object
     */
    private fun loadFromSharedPref() {
        val gson = Gson()
        val json: String? = preferences.getString(prefLastViewed, "")
        val previousItem: Item = gson.fromJson<Item>(json, Item::class.java)
        item = previousItem
        // load to ui
        loadDetails(previousItem)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        updateSharedPref(true)
    }

    /**
     * Update shared preference value
     */
    private fun updateSharedPref(isRemove:Boolean){

        val editor = preferences.edit()
        if ( isRemove ) {
            editor.clear()
        }
        else {
            val gson = Gson()
            val json = gson.toJson(item)
            editor.putString(prefLastViewed, json)
        }
        editor.apply()
    }
}