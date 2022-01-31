package com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.reviews.model

data class ReviewsResult(
    val author: String,
    val author_details: ReviewsAuthorDetails,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String
)