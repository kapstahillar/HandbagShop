package com.kapsta.handbagshop.ui.auth.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kapsta.handbagshop.data.dto.AuthUser
import com.kapsta.handbagshop.data.model.AuthRequest
import com.kapsta.handbagshop.framework.exceptions.InvalidUserInput
import com.kapsta.handbagshop.framework.service.data.Resource
import com.kapsta.handbagshop.service.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginFormViewModel @Inject constructor(
    private var authService: AuthService
) : ViewModel() {

    val userCode = MutableLiveData("104417")
    val username = MutableLiveData("gerk@shopz.com")
    val password = MutableLiveData("dkqTyh3YB5eAScbN")

    val errorMessage = MutableLiveData("")

    private var job: Job? = null

    private var userMutableLiveData: MutableLiveData<AuthUser>? = null

    fun getUser(): MutableLiveData<AuthUser> {
        if (userMutableLiveData == null) {
            userMutableLiveData = MutableLiveData()
        }
        return userMutableLiveData!!
    }

    fun loginUser(
        userCode: String,
        username: String,
        password: String,
    ) {
        this.userCode.value = userCode
        this.username.value = username
        this.password.value = password
        if (validateUser()) {
            makeLoginRequest()
        } else {
            throw InvalidUserInput
        }
    }

    private fun makeLoginRequest() {
        job = viewModelScope.launch {
            val user = authService.login(
                AuthRequest(
                    userCode.value ?: "",
                    username.value ?: "",
                    password.value ?: "",
                )
            )
            when (user) {
                is Resource.Error -> {
                    errorMessage.value = user.message
                }

                is Resource.Success -> {
                    getUser().value = user.data
                }

                else -> {}
            }

        }
    }

    private fun validateUser(): Boolean {
        if (userCode.value == "") {
            return false
        }
        if (username.value == "") {
            return false
        }
        if (password.value == "") {
            return false
        }
        return true
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}