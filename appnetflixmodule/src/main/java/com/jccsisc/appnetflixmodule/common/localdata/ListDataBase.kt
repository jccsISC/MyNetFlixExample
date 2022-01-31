package com.jccsisc.appnetflixmodule.common.localdata

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jccsisc.appnetflixmodule.common.localdata.model.MovieEntity

/****
 * Project: MyNetflixApp
 * From: com.jccsisc.appnetflixmodule
 * Created by Julio Cesar Camacho Silva on 26/01/2022 at 16:29
 * More info: https://www.facebook.com/juliocesar.camachosilva/
 * All rights reserved 2022.
 ***/
@Database(entities = [MovieEntity::class], version = 1)
abstract class ListDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}