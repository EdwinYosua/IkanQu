package com.capstoneapp.ikanqu.ui.home

import ModelResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneapp.ikanqu.network.ApiResult
import com.capstoneapp.ikanqu.repository.AppRepository
import kotlinx.coroutines.launch
import java.io.File

class HomeViewModel(private val appRepo: AppRepository) : ViewModel() {

    private val _analyzeResponse = MutableLiveData<ApiResult<ModelResponse>>()
    val analyzeResponse: MutableLiveData<ApiResult<ModelResponse>> by lazy { _analyzeResponse }


    fun logout() {
        viewModelScope.launch {
            appRepo.clearLoginData()
        }
    }

    fun analyzeImg(imgUri: File) {
        viewModelScope.launch {
            appRepo.analyzeImg(imgUri).collect {
                analyzeResponse.value = it
            }
        }
    }
}