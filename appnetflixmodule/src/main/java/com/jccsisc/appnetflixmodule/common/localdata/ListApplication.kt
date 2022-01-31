package com.jccsisc.appnetflixmodule.common.localdata

import android.app.Application
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore

/****
 * Project: MyNetflixApp
 * From: com.jccsisc.appnetflixmodule.common.localdata
 * Created by Julio Cesar Camacho Silva on 26/01/2022 at 16:32
 * More info: https://www.facebook.com/juliocesar.camachosilva/
 * All rights reserved 2022.
 ***/
val netjlix_preferences: MySharedPreferences by lazy { ListApplication.prefer!! }

class ListApplication : Application() {
    companion object {
        lateinit var database: ListDataBase
        var prefer: MySharedPreferences? = null
    }

    override fun onCreate() {
        super.onCreate()

        prefer = MySharedPreferences(applicationContext)
        database = Room.databaseBuilder(this, ListDataBase::class.java, "ListDatabase").build()
    }
}
