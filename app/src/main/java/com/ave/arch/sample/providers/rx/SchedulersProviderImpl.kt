package com.ave.arch.sample.providers.rx

import com.ave.arch.sample.domain.providers.rx.SchedulersProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulersProviderImpl : SchedulersProvider {
    override fun io(): Scheduler = Schedulers.io()
    override fun computation(): Scheduler = Schedulers.computation()
    override fun mainThread(): Scheduler = AndroidSchedulers.mainThread()
}