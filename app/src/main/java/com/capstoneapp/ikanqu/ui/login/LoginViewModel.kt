package com.capstoneapp.ikanqu.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneapp.ikanqu.network.ApiResult
import com.capstoneapp.ikanqu.network.response.LoginSuccess
import com.capstoneapp.ikanqu.repository.AppRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val appRepo: AppRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<ApiResult<LoginSuccess>>()
    private val _loginName = MutableLiveData<String?>()

    val loginResult: LiveData<ApiResult<LoginSuccess>> = _loginResult
    val loginName: LiveData<String?> = _loginName

    fun loginUser(email: String, pass: String) {
        viewModelScope.launch {
            appRepo.login(email, pass).collect {
                _loginResult.value = it
                if (it is ApiResult.ApiSuccess) {
                    _loginName.value = it.data.name
                }
            }
        }
    }
}