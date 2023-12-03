package com.kapsta.handbagshop.ui.auth.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kapsta.handbagshop.R
import com.kapsta.handbagshop.data.dto.AuthUser
import com.kapsta.handbagshop.ui.auth.viewmodel.LoginFormViewModel
import dagger.hilt.android.AndroidEntryPoint

interface LoginCallBackListener {
    fun onCallBack(user: AuthUser)
}

@AndroidEntryPoint
class LoginForm(private val loginCallBackListener: LoginCallBackListener) :
    Fragment(R.layout.activity_login) {

    private val loginFormModel: LoginFormViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userCodeText = view.findViewById<EditText>(R.id.usercode)
        userCodeText.setText(loginFormModel.userCode.value)
        val usernameText = view.findViewById<EditText>(R.id.username)
        usernameText.setText(loginFormModel.username.value)
        val passwordText = view.findViewById<EditText>(R.id.password)
        passwordText.setText(loginFormModel.password.value)
        val loginButton = view.findViewById<Button>(R.id.login)
        loginFormModel.getUser().observe(viewLifecycleOwner, Observer {
            loginCallBackListener.onCallBack(it)
        })
        loginFormModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "Invalid User credentials", Toast.LENGTH_SHORT).show()
        })
        loginButton.setOnClickListener(View.OnClickListener {
            loginButton.isEnabled = false
            loginUser(
                userCodeText.text.toString(),
                usernameText.text.toString(),
                passwordText.text.toString()
            )
            loginButton.isEnabled = true
        })
        loginButton.isEnabled = true
    }

    private fun loginUser(userCode: String, username: String, password: String) {
        loginFormModel.loginUser(userCode, username, password)
    }
}