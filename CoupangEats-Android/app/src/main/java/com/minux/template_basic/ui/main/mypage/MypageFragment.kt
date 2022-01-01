package com.minux.template_basic.ui.main.mypage

import android.content.Intent
import com.minux.template_basic.databinding.FragmentMypageBinding
import com.minux.template_basic.ui.BaseFragment
import com.minux.template_basic.ui.login.LoginActivity
import com.minux.template_basic.ui.siginup.SignUpActivity

class MypageFragment(): BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::inflate) {

    override fun initAfterBinding() {

        binding.mypageSignInBtn2.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

        binding.mypageSignupTv.setOnClickListener {
            val intent = Intent(requireContext(), SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}