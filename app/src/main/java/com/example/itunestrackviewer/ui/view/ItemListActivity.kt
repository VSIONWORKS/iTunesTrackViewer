package com.example.itunestrackviewer.ui.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itunestrackviewer.R
import com.example.itunestrackviewer.data.model.Item
import com.example.itunestrackviewer.data.remote.ApiService
import com.example.itunestrackviewer.ui.adapter.ItemAdapter
import com.example.itunestrackviewer.utils.ViewType
import com.example.itunestrackviewer.viewmodel.ListViewModel
import com.example.itunestrackviewer.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_item_list.*

class ItemListActivity : AppCompatActivity() {

    private lateinit var preferences : SharedPreferences
    private val prefLastViewed = "LAST_VIEWED"

    private lateinit var viewModel: ListViewModel
    private lateinit var featuredAdapter: ItemAdapter
    private lateinit var playlistAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        preferences = getSharedPreferences(prefLastViewed, Context.MODE_PRIVATE)

        /**
         * Check if there's a current viewed item
         * saved from shared preferences
         */
        if (preferences.getString(prefLastViewed, "")!!.isNotEmpty()) {
            startActivity(Intent(this, ItemDetailActivity::class.java))
        }

        initialize()
    }

    /**
     * Initialize all components
     */
    private fun initialize() {

        // initialize
        startShimmer()
        setUpViewModel()
        setUpAdapter()
        setUpFeaturedObserver()
        setUpPlayListObserver()
    }


    /**
     * Setup main view-model
     */
    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(this,ApiService())
        ).get(ListViewModel::class.java)
    }

    /**
     * Imitialize adapters
     * Setup the two recyclerview layout and adapter
     */
    private fun setUpAdapter() {

        val linearLayoutManagerFeatured = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        featuredAdapter = ItemAdapter(arrayListOf(),ViewType.FEATURED)
        itemVideosRecyclerView.layoutManager = linearLayoutManagerFeatured
        itemVideosRecyclerView.adapter = featuredAdapter

        val linearLayoutManagerPlaylist = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        playlistAdapter = ItemAdapter(arrayListOf(),ViewType.PLAYLIST)
        itemTracksRecyclerView.layoutManager = linearLayoutManagerPlaylist
        itemTracksRecyclerView.adapter = playlistAdapter
        itemTracksRecyclerView.isNestedScrollingEnabled = false
    }

    /**
     * Setup observer for featured list items
     */
    private fun setUpFeaturedObserver() {
        viewModel.featuredList.observe( this, Observer {
            if ( it.isNullOrEmpty() ) {
                viewModel.fetchItems()
            }
            else {
                loadFeaturedList(it)
            }
        })
    }

    /**
     * Setup observer for playlist items
     */
    private fun setUpPlayListObserver() {
        viewModel.playList.observe( this, Observer {
            if ( it.isNotEmpty() ) {
                loadPlaylist(it)
            }
        })
    }

    /**
     * load featured items to adapter
     */
    private fun loadFeaturedList(list:List<Item>) {
        featuredAdapter.loadList(list)
        featuredAdapter.notifyDataSetChanged()
        removeShimmer()
    }

    /**
     * load playlist items to adapter
     */
    private fun loadPlaylist(list:List<Item>) {
        playlistAdapter.loadList(list)
        playlistAdapter.notifyDataSetChanged()
        removeShimmer()
    }

    /**
     * Start Shimmer effects
     */
    private fun startShimmer() {
        shimmerListLayout.startShimmerAnimation()
    }

    /**
     * Remove Shimmer effects
     */
    private fun removeShimmer(){
        shimmerListLayout.stopShimmerAnimation()
        shimmerListLayout.visibility = View.GONE
        loadedView.visibility = View.VISIBLE
    }
}