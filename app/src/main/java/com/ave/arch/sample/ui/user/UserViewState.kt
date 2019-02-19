package com.ave.arch.sample.ui.user

import com.ave.arch.sample.domain.model.user.UserInfo

sealed class UserViewState
data class Data(val user: UserInfo) : UserViewState()
object Loading : UserViewState()
data class Error(val th: Throwable) : UserViewState()