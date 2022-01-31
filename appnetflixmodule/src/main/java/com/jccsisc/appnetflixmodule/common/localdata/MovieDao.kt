package com.jccsisc.appnetflixmodule.common.localdata

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jccsisc.appnetflixmodule.common.localdata.model.MovieEntity

@Dao
interface MovieDao {

 /*   @Query("SELECT * FROM movieEntity")
    suspend fun getAllMovies(): LiveData<MutableList<MovieEntity>>

    @Query("SELECT * FROM movieEntity WHERE type == :type")
    suspend fun getMovies(type: Int): LiveData<MutableList<MovieEntity>>

    @Query("SELECT * FROM movieEntity WHERE isFavorite")
    suspend fun getFavoriteMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntity)

    @Update
    suspend fun updateItem(itemEntity: MovieEntity): Int*/

}