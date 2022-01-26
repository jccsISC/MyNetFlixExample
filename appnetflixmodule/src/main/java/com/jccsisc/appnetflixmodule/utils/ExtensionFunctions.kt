package com.jccsisc.appnetflixmodule.utils

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

/****
 * Project: MyNetflixApp
 * From: com.jccsisc.appnetflixmodule.utils
 * Created by Julio Cesar Camacho Silva on 25/01/2022 at 22:53
 * More info: https://www.facebook.com/juliocesar.camachosilva/
 * All rights reserved 2022.
 ***/
inline fun <reified T : Activity> Activity.goToActivity(noinline init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.requireContext(), message, duration).show()
}

fun View.showView(show: Boolean = true) {
    if (show) this.visibility = View.VISIBLE else this.visibility = View.GONE
}

fun dayMonth(date: Date): String {
    val sdf = SimpleDateFormat("dd MMMM", Locale.forLanguageTag("es-MX"))
    return sdf.format(date)
}