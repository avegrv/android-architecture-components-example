package com.ave.arch.sample.di.component

import com.ave.arch.sample.di.module.UserModule
import com.ave.arch.sample.di.scope.UserScope
import com.ave.arch.sample.ui.user.UserViewModel
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = [UserModule::class])
interface UserComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): UserComponent
    }

    fun userViewModel(): UserViewModel
}