package com.jccsisc.appnetflixmodule.iu.fragments.maps.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.graphics.drawable.IconCompat
import com.google.android.gms.location.LocationServices
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.iu.fragments.maps.model.UserLocation
import com.jccsisc.appnetflixmodule.iu.fragments.maps.service.repository.ServiceRemoteRepository
import com.jccsisc.appnetflixmodule.utils.randomNumberId
import okhttp3.internal.notify

/****
 * Project: MyNetflixApp
 * From: com.jccsisc.appnetflixmodule.iu.fragments.maps.service
 * Created by Julio Cesar Camacho Silva on 29/01/2022 at 1:15
 * More info: https://www.facebook.com/juliocesar.camachosilva/
 * All rights reserved 2022.
 ***/
const val SERVICE_TAG = "MyService"

class MyService : Service() {
    var handler = Handler()
    private var isRunning = false

    companion object {
        val START_SERVICE = "START_SERVICE"
        val STOP_SERVICE = "STOP_SERVICE"
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            START_SERVICE -> {
                isRunning = true
                workingService()
            }
            STOP_SERVICE -> {

            }
        }

        return START_STICKY
    }

    private fun workingService() {
        var contador = 0

        handler.apply {
            val runnable = object : Runnable {
                override fun run() {
                    if (!isRunning) {
                        return
                    }

                    contador++
                    Log.d(SERVICE_TAG, "Contador: $contador")
                    postDelayed(this, 1000)
                    if (contador % 10 == 0) {
                        with(ServiceRemoteRepository()) {

                            getUserLocation(applicationContext) {
                                if (it != null) {
                                    saveLocation(UserLocation(latitud = it.latitude, longitud = it.longitude)) { result ->
                                        if (result) {
                                            createNotification(
                                                "Geolocalización",
                                                "Se subió correctamente"
                                            )
                                        } else {
                                            createNotification(
                                                "Geolocalización",
                                                "Falló al subir tu geolocalización"
                                            )
                                        }

                                        Log.d(MyService::class.java.simpleName, "Result -> $result")
                                    }
                                }
                            }
                        }
                    }
                }
            }

            postDelayed(runnable, 1000)
        }
    }

    @SuppressLint("MissingPermission")
    fun getUserLocation(context: Context, result: (location: Location?) -> Unit) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                result.invoke(location)
            }
        }
    }

    override fun onDestroy() {
        Log.d(SERVICE_TAG, "Servicio destruido.")
        handler = Handler()
        handler.removeCallbacksAndMessages(null)
        isRunning = false
        super.onDestroy()
    }

    private fun createNotification(
        title: String,
        contentText: String,
        icon: Int = R.drawable.ic_notification,
    ) {
        val idUnique = randomNumberId()
        val channelID = getString(R.string.app_name)
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelID, channelID, importance).apply {
                    description = channelID
                }

            val builder = NotificationCompat.Builder(applicationContext, channelID)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(contentText)

            with(NotificationManagerCompat.from(applicationContext)) {

                (applicationContext
                    .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).apply {
                    createNotificationChannel(channel)
                }
                builder.setSilent(false)

                notify(idUnique, builder.build())
            }
        }
    }
}

