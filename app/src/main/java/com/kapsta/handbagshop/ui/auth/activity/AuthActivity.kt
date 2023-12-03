package com.kapsta.handbagshop.ui.auth.activity

import android.R
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.kapsta.handbagshop.data.dto.AuthUser
import com.kapsta.handbagshop.ui.auth.fragment.LoginCallBackListener
import com.kapsta.handbagshop.ui.auth.fragment.LoginForm
import com.kapsta.handbagshop.ui.home.activity.HomeActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthActivity : AppCompatActivity(), LoginCallBackListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addLoginFragment()
    }

    private fun addLoginFragment() {
        val fm: FragmentManager = supportFragmentManager
        var fragment = fm.findFragmentByTag("loginForm")
        if (fragment == null) {
            val ft: FragmentTransaction = fm.beginTransaction()
            fragment = LoginForm(this)
            ft.add(R.id.content, fragment, "loginForm")
            ft.commit()
        }
    }

    override fun onCallBack(user: AuthUser) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("session_key", user.sessionKey)
        intent.putExtra("client_code", user.clientCode)
        startActivity(intent)
    }
}