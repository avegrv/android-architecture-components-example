package com.ave.arch.sample.di

import android.content.Context
import com.ave.arch.sample.di.component.*

internal object DI {

    lateinit var app: ApplicationComponent

    val user: ComponentHolder<UserComponent> = componentHolder(
            constructor = { app.userComponent().build() },
            destructor = {
                // destroy child's components here
            }
    )

    fun init(context: Context) {
        this.app = DaggerApplicationComponent
                .builder()
                .context(context)
                .build()
    }
}