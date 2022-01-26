package com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.frames.adapter

import android.content.ContentValues.TAG
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
import com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.frames.model.Backdrop

class FrameImagesAdapter: ListAdapter<Backdrop, FrameImagesAdapter.ImageViewHolder>(DiffCallback) {

    lateinit var onItemClickListener: (Backdrop) -> Unit

    companion object DiffCallback: DiffUtil.ItemCallback<Backdrop>() {

        override fun areItemsTheSame(oldItem: Backdrop, newItem: Backdrop): Boolean {
            return oldItem.file_path == newItem.file_path
        }

        override fun areContentsTheSame(oldItem: Backdrop, newItem: Backdrop): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieItem: Backdrop) = with(binding) {

            Glide.with(root.context)
                .load(root.context.getString(R.string.url_img, movieItem.file_path))
                .centerCrop()
                .into(imgMovie)

            cardMovie.animation = AnimationUtils.loadAnimation(root.context, R.anim.up)

            root.setOnClickListener {
                if (::onItemClickListener.isInitialized) {
                    onItemClickListener(movieItem)
                } else {
                    Log.e(TAG, root.context.getString(R.string.on_item_click_listener))
                }
            }
        }
    }
}