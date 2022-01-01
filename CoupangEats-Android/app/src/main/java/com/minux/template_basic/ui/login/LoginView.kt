package com.minux.template_basic.ui.login

import com.minux.template_basic.data.remote.auth.Auth

interface LoginView {
    fun onLoginLoading()
    fun onLoginSuccess(auth: Auth)
    fun onLoginFailure(code: Int, message: String)
}