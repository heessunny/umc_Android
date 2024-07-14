package com.example.practice.flo.data.ui.signin

import com.example.practice.flo.data.remote.Result

interface LoginView {
    fun onLoginSuccess(code : Int, result: Result)
    fun onLoginFailure()
}