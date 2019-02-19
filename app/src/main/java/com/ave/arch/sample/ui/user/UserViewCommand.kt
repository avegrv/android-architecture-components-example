package com.ave.arch.sample.ui.user


sealed class UserViewCommand
class ShowToast(val textRes: Int) : UserViewCommand()
