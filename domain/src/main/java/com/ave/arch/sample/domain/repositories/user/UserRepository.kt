package com.ave.arch.sample.domain.repositories.user

import com.ave.arch.sample.domain.model.user.UserInfo
import io.reactivex.Single

interface UserRepository {

    fun getUserInfo(): Single<UserInfo>
}