package com.jccsisc.appnetflixmodule.iu.fragments.gallery.adapter

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.databinding.ItemImageBinding
import com.jccsisc.appnetflixmodule.iu.fragments.maps.model.UserLocation

/****
 * Project: MyNetflixApp
 * From: com.jccsisc.appnetflixmodule.iu.fragments.gallery.adapter
 * Created by Julio Cesar Camacho Silva on 27/01/2022 at 12:18
 * More info: https://www.facebook.com/juliocesar.camachosilva/
 * All rights reserved 2022.
 ***/
class GalleryAdapter: ListAdapter<UserLocation, GalleryAdapter.ImageViewHolder>(DiffCallback) {

    lateinit var onItemClickListener: (UserLocation) -> Unit

    companion object DiffCallback: DiffUtil.ItemCallback<UserLocation>() {

        override fun areItemsTheSame(oldItem: UserLocation, newItem: UserLocation): Boolean {
            return oldItem.idUser == newItem.idUser
        }

        override fun areContentsTheSame(oldItem: UserLocation, newItem: UserLocation): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryAdapter.ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryAdapter.ImageViewHolder, position: Int) {
        val image = getItem(position)
        holder.bind(image)
    }

    inner class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageItem: UserLocation) = with(binding) {

            Glide.with(root.context)
                .load(imageItem.image)
                .centerCrop()
                .into(imgMovie)

            cardMovie.animation = AnimationUtils.loadAnimation(root.context, R.anim.up)

            root.setOnClickListener {
                if (::onItemClickListener.isInitialized) {
                    onItemClickListener(imageItem)
                } else {
                    Log.e(ContentValues.TAG, root.context.getString(R.string.on_item_click_listener))
                }
            }
        }
    }
}