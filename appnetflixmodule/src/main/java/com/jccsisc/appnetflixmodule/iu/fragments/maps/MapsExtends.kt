package com.jccsisc.appnetflixmodule.iu.fragments.maps

import android.content.Intent
import android.os.Handler
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.common.localdata.netjlix_preferences
import com.jccsisc.appnetflixmodule.iu.fragments.maps.service.MyService
import com.jccsisc.appnetflixmodule.iu.fragments.maps.service.MyService.Companion.START_SERVICE
import com.jccsisc.appnetflixmodule.iu.fragments.maps.service.MyService.Companion.STOP_SERVICE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/****
 * Project: MyNetflixApp
 * From: com.jccsisc.appnetflixmodule.iu.fragments.maps
 * Created by Julio Cesar Camacho Silva on 28/01/2022 at 14:15
 * More info: https://www.facebook.com/juliocesar.camachosilva/
 * All rights reserved 2022.
 ***/
fun MapsFragment.initElements() {
    mBinding.apply {
        requestLocationPermission()

        imgPlayStopService.setOnClickListener {

            netjlix_preferences.isRunningService = !netjlix_preferences.isRunningService
            viewModel.statusService.value = netjlix_preferences.isRunningService

            val intent = Intent(
                requireContext(),
                MyService::class.java
            )
            if (netjlix_preferences.isRunningService) {

                requireActivity().startService(
                    intent.apply {
                        action = START_SERVICE
                    })
            } else {
                requireActivity().stopService(
                    intent.apply {
                    action = STOP_SERVICE
                })
            }
        }
    }
}

fun MapsFragment.initObserversMaps() {
    viewModel.apply {
        mBinding.apply {
            statusService.observe(this@initObserversMaps, {
                if (it) {
                    imgPlayStopService.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_stop_service
                        )
                    )
                    tvServiceStatus.text = getString(R.string.service_running)
                } else {
                    imgPlayStopService.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_play_service
                        )
                    )
                    tvServiceStatus.text = getString(R.string.stopped_service)
                }
            })
        }
    }
}