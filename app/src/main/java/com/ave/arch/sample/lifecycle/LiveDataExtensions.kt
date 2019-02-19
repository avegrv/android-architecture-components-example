package com.ave.arch.sample.lifecycle

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import java.util.*

fun <T> MutableLiveData<T>.onNext(next: T) {
    this.value = next
}

inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(aClass: Class<T>): T = f() as T
    }
}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(viewModelFactory: ViewModelProvider.Factory? = null): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.getViewModel(viewModelFactory: ViewModelProvider.Factory? = null): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}

inline fun <reified T : Any, reified L : LiveData<T?>> FragmentActivity.observe(
        liveData: L,
        noinline block: (T) -> Unit
) {
    liveData.observe(this, Observer { it?.let { block.invoke(it) } })
}

inline fun <reified T : Any, reified L : LiveData<T?>> Fragment.observe(
        liveData: L,
        noinline block: (T) -> Unit
) {
    liveData.observe(this.viewLifecycleOwner, Observer { it?.let { block.invoke(it) } })
}

inline fun <reified T : Any, reified L : CommandsLiveData<T>> LifecycleOwner.observe(
        liveData: L,
        noinline block: (T) -> Unit
) {
    liveData.observe(this, Observer<LinkedList<T>> { commands ->
        if (commands == null) {
            return@Observer
        }
        var command: T?
        do {
            command = commands.poll()
            if (command != null) {
                block.invoke(command)
            }
        } while (command != null)
    })
}

