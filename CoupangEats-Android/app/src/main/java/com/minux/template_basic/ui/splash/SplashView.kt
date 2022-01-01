package com.minux.template_basic.ui.splash

import com.minux.template_basic.data.remote.auth.Auth

interface SplashView {
    fun onAutoLoginLoading()
    fun onAutoLoginSuccess()
    fun onAutoLoginFailure(code: Int, message: String)
}