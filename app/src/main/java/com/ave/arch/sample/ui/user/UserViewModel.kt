package com.ave.arch.sample.ui.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ave.arch.sample.R
import com.ave.arch.sample.domain.repositories.user.UserRepository
import com.ave.arch.sample.lifecycle.CommandsLiveData
import com.ave.arch.sample.lifecycle.onNext
import com.ave.arch.sample.ui.base.viewmodel.BaseViewModel
import javax.inject.Inject

class UserViewModel @Inject constructor(
        private val userRepository: UserRepository
) : BaseViewModel() {

    companion object {
        private const val LOG_TAG = "LOG_TAG"
    }

    val user: MutableLiveData<UserViewState> = MutableLiveData()

    val command: CommandsLiveData<UserViewCommand> = CommandsLiveData()

    init {
        loadUserInfo()
    }

    fun onGetPhoneNumberClicked() {
        command.onNext(ShowToast(R.string.get_phone_number_stub))
    }

    private fun loadUserInfo() {
        userRepository
                .getUserInfo()
                .toObservable()
                .map<UserViewState>(::Data)
                .startWith(Loading)
                .onErrorReturn(::Error)
                .subscribe(user::onNext) { th -> Log.e(LOG_TAG, "user", th) }
                .disposeOnViewModelDestroy()
    }
}
