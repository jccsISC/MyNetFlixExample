package com.jccsisc.appnetflixmodule.common.localdata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jccsisc.appnetflixmodule.common.localdata.model.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieEntity")
    suspend fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntity)

}