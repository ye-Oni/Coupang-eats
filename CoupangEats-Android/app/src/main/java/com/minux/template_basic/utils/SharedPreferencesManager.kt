package com.minux.template_basic.utils

import com.minux.template_basic.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.minux.template_basic.ApplicationClass.Companion.mSharedPreferences

fun saveJwt(jwtToken: String) {
    val editor = mSharedPreferences.edit()
    editor.putString(X_ACCESS_TOKEN, jwtToken)

    editor.apply()
}

fun getJwt(): String? = mSharedPreferences.getString(X_ACCESS_TOKEN, null)
