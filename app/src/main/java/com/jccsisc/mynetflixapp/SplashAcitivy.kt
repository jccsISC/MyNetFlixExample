package com.jccsisc.mynetflixapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jccsisc.appnetflixmodule.utils.goToActivity

class SplashAcitivy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goToActivity<MainActivity>()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }
}