package com.ave.arch.sample.ui.user

import android.os.Bundle
import com.redmadrobot.lib.sd.LoadingStateDelegate
import com.ave.arch.sample.R
import com.ave.arch.sample.di.DI
import com.ave.arch.sample.lifecycle.Event
import com.ave.arch.sample.lifecycle.getViewModel
import com.ave.arch.sample.lifecycle.observe
import com.ave.arch.sample.lifecycle.viewModelFactory
import com.ave.arch.sample.ui.base.ShowToast
import com.ave.arch.sample.ui.base.activity.BaseActivity
import com.ave.arch.sample.ui.base.layout.StubStateData
import kotlinx.android.synthetic.main.activity_main.*

class InfoActivity : BaseActivity() {

    override val layoutResource = R.layout.activity_main

    private lateinit var screenState: LoadingStateDelegate
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenState = LoadingStateDelegate(content, progress, zero_stub)

        val viewModelFactory = viewModelFactory { DI.user.userViewModel() }
        viewModel = getViewModel(viewModelFactory)
        observe(viewModel.user, this::onUserChanged)
        observe(viewModel.events, this::onEventReceived)
    }

    private fun onUserChanged(viewState: UserViewState) {
        when (viewState) {
            is Data -> {
                screenState.showContent()
                content_name.text = viewState.user.firstName
                content_get_phone.setOnClickListener { viewModel.onGetPhoneNumberClicked() }
            }
            is Loading -> {
                screenState.showLoading()
            }
            is Error -> {
                screenState.showStub(
                        StubStateData(
                                iconResId = R.mipmap.ic_launcher,
                                titleResId = R.string.loading_zero_title,
                                descriptionResId = R.string.loading_zero_description
                        )
                )
            }
        }
    }

    private fun onEventReceived(event: Event) {
        when (event) {
            is ShowToast -> {
                showMessage(getString(event.textRes))
            }
        }
    }
}
