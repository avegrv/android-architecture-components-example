package com.ave.arch.sample.di.module

import android.content.Context
import com.ave.arch.sample.domain.providers.rx.SchedulersProvider
import com.ave.arch.sample.domain.providers.system.SystemInfoProvider
import com.ave.arch.sample.di.scope.ApplicationScope
import com.ave.arch.sample.providers.rx.SchedulersProviderImpl
import com.ave.arch.sample.providers.system.SystemInfoDataProvider
import dagger.Module
import dagger.Provides

@Module
internal class ApplicationModule {


    @Provides
    @ApplicationScope
    fun provideSchedulersProvider(): SchedulersProvider = SchedulersProviderImpl()

    @Provides
    @ApplicationScope
    fun provideSystemInfoProvider(context: Context): SystemInfoProvider = SystemInfoDataProvider(context)
}