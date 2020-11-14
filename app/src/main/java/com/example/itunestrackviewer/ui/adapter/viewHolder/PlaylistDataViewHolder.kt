package com.example.itunestrackviewer.ui.adapter.viewHolder

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.itunestrackviewer.R
import com.example.itunestrackviewer.data.model.Item
import com.example.itunestrackviewer.ui.view.ItemDetailActivity
import kotlinx.android.synthetic.main.track_item.view.*

/**
 * View holder for our track items
 * Use on our Recycler view adapter
 * Used Glide for image caching
 */
class PlaylistDataViewHolder(itemView: View) : RecyclerView.ViewHolder( itemView ) {

    fun bind(item : Item?) {
        if (item != null) {

            val context = itemView.context

            Glide.with(context).load(item!!.artworkUrl100)
                .into(itemView.itemArtWork)

            if ( item.trackName.isNotEmpty() ) {
                itemView.itemTrackName.text = item.trackName
            }
            else {
                itemView.itemTrackName.text = item.collectionName
            }

            if ( item.trackPrice.isNotEmpty() ) {
                itemView.itemPrice.text = item.currency + " " + item.trackPrice
            }
            else {
                itemView.itemPrice.text = item.currency + " " + item.collectionPrice
            }

            if (item.viewed) {
                itemView.itemTrackName.setTextColor(
                    ContextCompat.getColor(context, R.color.colorTextSelected)
                )
            }
            else {
                itemView.itemTrackName.setTextColor(
                    ContextCompat.getColor(context, R.color.colorText)
                )
            }

            itemView.itemGenre.text = item.primaryGenreName

            itemView.setOnClickListener {
                val intent = Intent(context, ItemDetailActivity::class.java)
                intent.putExtra("ITEM_OBJECT", item)
                itemView.context.startActivity(intent)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): PlaylistDataViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.track_item, parent, false)
            return PlaylistDataViewHolder(view)
        }
    }
}