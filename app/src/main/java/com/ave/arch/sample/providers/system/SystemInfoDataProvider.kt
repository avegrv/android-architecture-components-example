package com.ave.arch.sample.providers.system

import android.content.Context
import android.net.ConnectivityManager
import com.ave.arch.sample.domain.providers.system.SystemInfoProvider
import javax.inject.Inject

class SystemInfoDataProvider @Inject constructor(private val context: Context) : SystemInfoProvider {

    override fun hasNetwork(): Boolean {
        val appContext = context.applicationContext
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        return connectivityManager?.activeNetworkInfo?.isConnected ?: false
    }
}