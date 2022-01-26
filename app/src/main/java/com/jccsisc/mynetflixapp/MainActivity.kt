package com.jccsisc.mynetflixapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.jccsisc.appnetflixmodule.iu.fragments.maps.MapsFragment
import com.jccsisc.mynetflixapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.apply {

            val boottomNavigatioView = bottomNavigation
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

            val navController = navHostFragment.navController

            boottomNavigatioView.setupWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when(destination.id) {
                    R.id.mapsFragment -> {
                        boottomNavigatioView.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.bg_maps))
                    }
                    else -> {
                        boottomNavigatioView.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.primary_color))
                    }
                }
            }

        }
    }
}