package com.minux.template_basic.ui.login

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import com.minux.template_basic.R
import com.minux.template_basic.data.entities.User
import com.minux.template_basic.data.remote.auth.Auth
import com.minux.template_basic.data.remote.auth.AuthService
import com.minux.template_basic.databinding.ActivityLoginBinding
import com.minux.template_basic.ui.BaseActivity
import com.minux.template_basic.ui.main.MainActivity
import com.minux.template_basic.ui.siginup.SignUpActivity
import com.minux.template_basic.utils.saveJwt

class LoginActivity: BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate), LoginView, View.OnClickListener {

    private var hideState : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.loginInputPasswordIv.setOnClickListener {
            hidePwd(hideState)
        }
    }

    override fun initAfterBinding() {
        binding.loginSignUpTv.setOnClickListener(this)
        binding.loginSignInBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.loginSignUpTv -> startNextActivity(SignUpActivity::class.java)
            binding.loginSignInBtn -> login()
        }
    }

    private fun hidePwd(newPwdState : Boolean)
    {
        hideState = !newPwdState

        if(hideState)  // 숨김 상태
        {
            binding.loginInputPasswordIv.setImageResource(R.drawable.btn_input_password)
            binding.loginPwdEt.transformationMethod = PasswordTransformationMethod.getInstance()
        }
        else  // 안 숨김 상태
        {
            binding.loginInputPasswordIv.setImageResource(R.drawable.btn_input_password_off)
            binding.loginPwdEt.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }
    }

    private fun login() {
        if (binding.loginIdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.loginPwdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val email = binding.loginIdEt.text.toString()
        val password = binding.loginPwdEt.text.toString()
        val user = User(email, password, "", "")

        AuthService.login(this, user)
    }

    override fun onLoginLoading() {
        binding.loginLoadingPb.visibility = View.VISIBLE
    }

    override fun onLoginSuccess(auth: Auth) {
        binding.loginLoadingPb.visibility = View.GONE
        Toast.makeText(this, "로그인이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        startActivityWithClear(MainActivity::class.java)
    }

    override fun onLoginFailure(code: Int, message: String) {
        binding.loginLoadingPb.visibility = View.GONE

        when(code) {
            2000, 2015, 2016, 3014, 4000, 4001, 4002, 4003 -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this, "오류가 발생하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}