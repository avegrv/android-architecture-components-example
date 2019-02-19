package com.ave.arch.sample.di.component

class ComponentHolder<out T>(
        private val constructor: () -> T,
        private val destructor: () -> Unit
) {

    private var component: T? = null

    fun get(): T {
        val component = component
        return if (component == null) {
            val newComponent = constructor()
            this.component = newComponent
            newComponent
        } else {
            component
        }
    }

    fun destroy() {
        destructor.invoke()
        component = null
    }
}

inline fun <reified T> componentHolder(noinline constructor: () -> T): ComponentHolder<T> {
    return ComponentHolder(constructor, {})
}

inline fun <reified T> componentHolder(
        noinline constructor: () -> T,
        noinline destructor: () -> Unit = {}
): ComponentHolder<T> {
    return ComponentHolder(constructor, destructor)
}