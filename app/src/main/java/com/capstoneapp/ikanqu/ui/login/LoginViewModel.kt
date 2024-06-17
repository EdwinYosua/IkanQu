package com.capstoneapp.ikanqu.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneapp.ikanqu.network.ApiResult
import com.capstoneapp.ikanqu.network.response.LoginResponse
import com.capstoneapp.ikanqu.repository.AppRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val appRepo: AppRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<ApiResult<LoginResponse>>()
    val loginResult: LiveData<ApiResult<LoginResponse>> = _loginResult


    fun loginUser(email: String, pass: String) {
        viewModelScope.launch {
            appRepo.login(email, pass).collect {
                _loginResult.value = it
            }
        }
    }
}