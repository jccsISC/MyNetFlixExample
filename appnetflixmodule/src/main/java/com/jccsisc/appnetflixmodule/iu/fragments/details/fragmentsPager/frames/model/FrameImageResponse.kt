package com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.frames.model

data class FrameImageResponse(
    val backdrops: List<Backdrop>,
    val id: Int,
    val logos: List<Logo>,
    val posters: List<Poster>
)