package com.ave.arch.sample.di.module

import android.content.Context
import com.ave.arch.sample.domain.providers.rx.SchedulersProvider
import com.ave.arch.sample.domain.providers.system.SystemInfoProvider
import com.ave.arch.sample.domain.repositories.user.UserRepository
import com.ave.arch.sample.di.scope.UserScope
import dagger.Module
import dagger.Provides
import ave.arch.sample.data.delegate.DataLayerDelegate
import ave.arch.sample.data.network.api.user.UserApi
import ave.arch.sample.data.network.factory.AppApiFactory
import ave.arch.sample.data.repository.user.UserDataRepository
import ave.arch.sample.data.storage.db.AppDatabase

@Module
internal class UserModule {

    @Provides
    @UserScope
    fun provideAppDatabase(context: Context) = DataLayerDelegate.provideAppDatabase(context)

    @Provides
    @UserScope
    fun provideAppApi() = AppApiFactory()

    @Provides
    @UserScope
    fun provideUserApi(appApiFactory: AppApiFactory) = appApiFactory.create(UserApi::class.java)

    @Provides
    @UserScope
    fun provideUserRepository(
            schedulers: SchedulersProvider,
            systemInfoProvider: SystemInfoProvider,
            userApi: UserApi,
            dataBase: AppDatabase
    ): UserRepository {
        return UserDataRepository(schedulers, systemInfoProvider, userApi, dataBase.userEntityDao())
    }
}