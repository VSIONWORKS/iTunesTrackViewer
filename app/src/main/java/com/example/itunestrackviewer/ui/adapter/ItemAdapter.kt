package com.example.itunestrackviewer.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.itunestrackviewer.data.model.Item
import com.example.itunestrackviewer.ui.adapter.viewHolder.FeaturedDataViewHolder
import com.example.itunestrackviewer.ui.adapter.viewHolder.PlaylistDataViewHolder
import com.example.itunestrackviewer.utils.ViewType

/**
 * Adapter with tow view holder
 * for handling the feature list and playlist items
 */
class ItemAdapter(private val arrayList: ArrayList<Item>, private val view: ViewType):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (view) {
            ViewType.FEATURED -> FeaturedDataViewHolder.create(parent)
            ViewType.PLAYLIST -> PlaylistDataViewHolder.create(parent)
        }
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (view) {
            ViewType.FEATURED -> (holder as FeaturedDataViewHolder).bind(arrayList[position])
            ViewType.PLAYLIST -> (holder as PlaylistDataViewHolder).bind(arrayList[position])
        }
    }

    fun loadList(list: List<Item>) {
        arrayList.clear()
        arrayList.addAll(list)
    }
}