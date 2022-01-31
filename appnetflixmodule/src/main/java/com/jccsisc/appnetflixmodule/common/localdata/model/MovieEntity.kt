package com.jccsisc.appnetflixmodule.common.localdata.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/****
 * Project: MyNetflixApp
 * From: com.jccsisc.appnetflixmodule.common.localdata.model
 * Created by Julio Cesar Camacho Silva on 26/01/2022 at 10:08
 * More info: https://www.facebook.com/juliocesar.camachosilva/
 * All rights reserved 2022.
 ***/
@Entity(tableName = "movieEntity")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val adult: Boolean = false,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String = "",
    @ColumnInfo(name = "original_language")
    val original_language: String = "",
    @ColumnInfo(name = "original_title")
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = -1.0,
    @ColumnInfo(name = "poster_path")
    val poster_path: String = "",
    @ColumnInfo(name = "release_date")
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double = -1.0,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int = -1,
    @ColumnInfo(name = "movie_type")
    val movie_type: String = "",
    val type: Int = 0,
    var isFavorite: Boolean = false
)
