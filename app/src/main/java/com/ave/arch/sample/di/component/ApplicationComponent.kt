package com.ave.arch.sample.di.component

import android.content.Context
import com.ave.arch.sample.di.module.ApplicationModule
import com.ave.arch.sample.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {

    fun userComponent(): UserComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(appContext: Context): Builder

        fun build(): ApplicationComponent
    }
}