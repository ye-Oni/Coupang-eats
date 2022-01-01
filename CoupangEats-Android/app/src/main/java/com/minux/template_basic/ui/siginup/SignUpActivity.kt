package com.minux.template_basic.ui.siginup

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import com.minux.template_basic.R
import com.minux.template_basic.data.entities.User
import com.minux.template_basic.data.remote.auth.AuthService
import com.minux.template_basic.databinding.ActivitySignupBinding
import com.minux.template_basic.ui.BaseActivity

class SignUpActivity: BaseActivity<ActivitySignupBinding>(ActivitySignupBinding::inflate), SignUpView, View.OnClickListener {

    private var hideState : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.signupInputPasswordIv.setOnClickListener {
            hidePwd(hideState)
        }
    }

    private fun hidePwd(newPwdState : Boolean)
    {
        hideState = !newPwdState

        if(hideState)  // 숨김 상태
        {
            binding.signupInputPasswordIv.setImageResource(R.drawable.btn_input_password)
            binding.signupPwdEt.transformationMethod = PasswordTransformationMethod.getInstance()
        }
        else  // 안 숨김 상태
        {
            binding.signupInputPasswordIv.setImageResource(R.drawable.btn_input_password_off)
            binding.signupPwdEt.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }
    }

    override fun initAfterBinding() {
        binding.signUpBackIv.setOnClickListener(this)
        binding.signUpSignUpBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v == null) return

        when(v) {
            binding.signUpBackIv -> finish()
            binding.signUpSignUpBtn -> signUp()
        }
    }

    private fun getUser(): User {
        val email: String = binding.signupIdEt.text.toString()
        val pwd: String = binding.signupPwdEt.text.toString()
        val name: String = binding.signupNameEt.text.toString()
        val phoneNum : String = binding.signupPhoneNoEt.text.toString()

        return User(email, pwd, name, phoneNum)
    }

    private fun signUp() {
        if (binding.signupIdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signupPwdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "비밀번호 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signupNameEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이름 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.signupPhoneNoEt.text.toString().isEmpty()) {
            Toast.makeText(this, "전화번호 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        AuthService.signUp(this, getUser())
    }

    override fun onSignUpLoading() {
        binding.signUpLoadingPb.visibility = View.VISIBLE
    }

    override fun onSignUpSuccess() {
        binding.signUpLoadingPb.visibility = View.GONE
        Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()

        finish()
    }

    override fun onSignUpFailure(code: Int, message: String) {
        binding.signUpLoadingPb.visibility = View.GONE

        when(code) {
            2000, 2015, 2016, 2017, 4000, 4001, 4002 -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this, "오류가 발생하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}