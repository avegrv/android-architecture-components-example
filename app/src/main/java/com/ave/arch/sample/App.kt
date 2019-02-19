package com.ave.arch.sample

import android.app.Application
import com.ave.arch.sample.di.DI

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        DI.init(this)
    }
}