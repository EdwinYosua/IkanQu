package com.capstoneapp.ikanqu.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneapp.ikanqu.network.ApiResult
import com.capstoneapp.ikanqu.network.response.RegisterResponse
import com.capstoneapp.ikanqu.repository.AppRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val appRepo: AppRepository) : ViewModel() {

    private val _registerResponse = MutableLiveData<ApiResult<RegisterResponse>>()
    val registerResponse: LiveData<ApiResult<RegisterResponse>> by lazy { _registerResponse }

    fun registerUser(
        name: String, email: String, password: String
    ) {
        viewModelScope.launch {
            appRepo.register(name, email, password).collect {
                _registerResponse.value = it
            }
        }
    }
}