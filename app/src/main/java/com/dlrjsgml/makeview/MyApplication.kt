package com.dlrjsgml.makeview

import android.app.Application

class MyApplication : Application(){
    companion object {
        lateinit var prefs: PreferenceUtil // datastore
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}