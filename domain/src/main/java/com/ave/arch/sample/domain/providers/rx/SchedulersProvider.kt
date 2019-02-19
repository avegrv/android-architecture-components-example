package com.ave.arch.sample.domain.providers.rx

import io.reactivex.Scheduler

interface SchedulersProvider {
    fun io(): Scheduler
    fun computation(): Scheduler
    fun mainThread(): Scheduler
}
