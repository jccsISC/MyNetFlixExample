package com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.reviews.adapter

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
import com.jccsisc.appnetflixmodule.databinding.ItemReviewBinding
import com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.reviews.model.ReviewsResult
import java.text.SimpleDateFormat
import java.util.*

class ReviewsAdapter : ListAdapter<ReviewsResult, ReviewsAdapter.ReviewViewHolder>(DiffCallback) {

    lateinit var onItemClickListener: (ReviewsResult) -> Unit

    companion object DiffCallback : DiffUtil.ItemCallback<ReviewsResult>() {

        override fun areItemsTheSame(oldItem: ReviewsResult, newItem: ReviewsResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ReviewsResult, newItem: ReviewsResult): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }

    inner class ReviewViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(reviewItem: ReviewsResult) = with(binding) {

            root.context.apply {
                reviewItem.apply {
                    if (author_details.avatar_path != null) {
                        if (author_details.avatar_path.contains("https")) {
                            Glide.with(root.context)
                                .load(author_details.avatar_path.substring(1, author_details.avatar_path.length))
                                .centerCrop()
                                .into(imgUser)
                        }else {
                            Glide.with(root.context)
                                .load(getString(R.string.url_img, author_details.avatar_path))
                                .centerCrop()
                                .into(imgUser)
                        }
                    }

                    cardReview.animation = AnimationUtils.loadAnimation(root.context, R.anim.up)
                    txtAutor.text = getString(R.string.author, author)

                    author_details.apply {
                        txtName.text = getString(R.string.name, name)
                        txtUserName.text = getString(R.string.user_name, username)
                        txtRating.text = getString(R.string.raiting, rating?.toString() ?: "0")
                    }.toString()

                    txtContent.text = content

                    val formatterDate = SimpleDateFormat("dd-MM-yy", Locale.forLanguageTag("es-MX"))
                    val date = formatterDate.parse(created_at.substring(0, 10))
                    val day = SimpleDateFormat("EEEE dd MMMM", Locale.forLanguageTag("es-MX")).format(date)
                    txtCreated.text = getString(R.string.created_content, day)

                    root.setOnClickListener {
                        if (::onItemClickListener.isInitialized) {
                            onItemClickListener(this)
                        } else {
                            Log.e(TAG, root.context.getString(R.string.on_item_click_listener))
                        }
                    }
                }
            }
        }
    }
}