package com.ave.arch.sample.lifecycle

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import java.util.*

class EventsQueue : MutableLiveData<Queue<Event>>() {

    @MainThread
    fun offer(event: Event) {
        val queue = value ?: LinkedList()
        queue.add(event)
        value = queue
    }
}