package com.ave.arch.sample.domain.providers.system

interface SystemInfoProvider {
    fun hasNetwork(): Boolean
}