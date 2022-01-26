package com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.reviews.model

data class ReviewsResponseModel(
    val id: Int,
    val page: Int,
    val results: List<ReviewsResult>,
    val total_pages: Int,
    val total_results: Int
)