package com.jccsisc.appnetflixmodule.common.localdata

import android.content.Context
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.file_name
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.key_service

/****
 * Project: MyNetflixApp
 * From: com.jccsisc.appnetflixmodule.iu.fragments.maps
 * Created by Julio Cesar Camacho Silva on 28/01/2022 at 21:12
 * More info: https://www.facebook.com/juliocesar.camachosilva/
 * All rights reserved 2022.
 ***/
class MySharedPreferences(context: Context) {
    private val fileName = file_name
    val prefer = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)

    var isRunningService: Boolean
    get() = prefer.getBoolean(key_service, false)
    set(value) = prefer.edit().putBoolean(key_service, value).apply()

}