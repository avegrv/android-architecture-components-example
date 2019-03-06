package com.ave.arch.sample.di

import android.content.Context
import com.ave.arch.sample.di.component.*

internal object DI {

    lateinit var app: ApplicationComponent

    val user by lazy { app.userComponent().build() }

    fun init(context: Context) {
        this.app = DaggerApplicationComponent
                .builder()
                .context(context)
                .build()
    }
}