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
internal abstract class ApplicationModule {


    @Module
    companion object {

        @JvmStatic
        @Provides
        @ApplicationScope
        internal fun provideSchedulersProvider(): SchedulersProvider = SchedulersProviderImpl()

        @JvmStatic
        @Provides
        @ApplicationScope
        internal fun provideSystemInfoProvider(context: Context): SystemInfoProvider = SystemInfoDataProvider(context)
    }
}