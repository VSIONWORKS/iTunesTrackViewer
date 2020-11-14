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
import kotlinx.android.synthetic.main.video_item.view.*

/**
 * View holder for our Featured items
 * Use on our Recycler view adapter
 * Used Glide for image caching
 */
class FeaturedDataViewHolder(itemView: View) : RecyclerView.ViewHolder( itemView ) {

    fun bind(item : Item?) {

        val context = itemView.context

        Glide.with(context).load(item!!.artworkUrl100)
            .into(itemView.itemVideoDetailArtWork)

        if (item != null) {
            itemView.itemFeaturedName.text = item.trackName
            itemView.itemFeaturedGenre.text = item.primaryGenreName
            itemView.itemFeaturedPrice.text = item.currency + " " + item.trackPrice

            if (item.viewed) {
                itemView.itemFeaturedName.setTextColor(
                    ContextCompat.getColor(context, R.color.colorTextSelected)
                )
            }
            else {
                itemView.itemFeaturedName.setTextColor(
                    ContextCompat.getColor(context, R.color.colorText)
                )
            }

            itemView.setOnClickListener {
                val intent = Intent(context, ItemDetailActivity::class.java)
                intent.putExtra("ITEM_OBJECT", item)
                itemView.context.startActivity(intent)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): FeaturedDataViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.video_item, parent, false)
            return FeaturedDataViewHolder(view)
        }
    }
}