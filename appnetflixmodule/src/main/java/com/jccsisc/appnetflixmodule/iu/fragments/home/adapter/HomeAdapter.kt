package com.jccsisc.appnetflixmodule.iu.fragments.home.adapter

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
import com.jccsisc.appnetflixmodule.databinding.ItemMovieBinding
import com.jccsisc.appnetflixmodule.iu.fragments.home.model.HomeMovieResponse

class HomeAdapter: ListAdapter<HomeMovieResponse, HomeAdapter.MovieViewHolder>(DiffCallback) {

    lateinit var onItemClickListener: (HomeMovieResponse) -> Unit

    companion object DiffCallback: DiffUtil.ItemCallback<HomeMovieResponse>() {

        override fun areItemsTheSame(oldItem: HomeMovieResponse, newItem: HomeMovieResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HomeMovieResponse, newItem: HomeMovieResponse): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieItem: HomeMovieResponse) = with(binding) {

            Glide.with(root.context)
                .load(root.context.getString(R.string.url_img, movieItem.poster_path))
                .centerCrop()
                .into(imgMovie)

            cardMovie.animation = AnimationUtils.loadAnimation(root.context, R.anim.slide_card)

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