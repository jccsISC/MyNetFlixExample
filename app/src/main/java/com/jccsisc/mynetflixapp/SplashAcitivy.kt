package com.jccsisc.mynetflixapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jccsisc.appnetflixmodule.common.localdata.netjlix_preferences
import com.jccsisc.appnetflixmodule.utils.goToActivity
import com.jccsisc.mynetflixapp.main.MainActivity

class SplashAcitivy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goToActivity<MainActivity>()
        netjlix_preferences.isRunningService = false
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }
}