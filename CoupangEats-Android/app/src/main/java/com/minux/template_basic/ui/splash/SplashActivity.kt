package com.minux.template_basic.ui.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.minux.template_basic.data.remote.auth.Auth
import com.minux.template_basic.data.remote.auth.AuthService
import com.minux.template_basic.databinding.ActivitySplashBinding
import com.minux.template_basic.ui.BaseActivity
import com.minux.template_basic.ui.login.LoginActivity
import com.minux.template_basic.ui.main.MainActivity
import com.minux.template_basic.utils.saveJwt

class SplashActivity: BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate), SplashView {

    override fun initAfterBinding() {
        Handler(Looper.getMainLooper()).postDelayed({
//            autoLogin()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 2000)
    }

    private fun autoLogin() {
        AuthService.autoLogin(this)
    }

    override fun onAutoLoginLoading() {

    }

    override fun onAutoLoginSuccess() {
        startActivityWithClear(MainActivity::class.java)
    }

    override fun onAutoLoginFailure(code: Int, message: String) {
        startActivityWithClear(LoginActivity::class.java)
    }
}