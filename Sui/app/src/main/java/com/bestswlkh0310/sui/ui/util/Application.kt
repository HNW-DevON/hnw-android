
package com.bestswlkh0310.sui.ui.util

import android.app.Application

class Application: Application() {
    companion object {
        lateinit var prefs: PreferenceManager
    }

//    override fun onCreate() {
//        super.onCreate()
//        prefs = PreferenceManager(applicationContext)
//    }
}