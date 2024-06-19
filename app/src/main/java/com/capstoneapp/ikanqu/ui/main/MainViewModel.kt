package com.capstoneapp.ikanqu.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstoneapp.ikanqu.repository.AppRepository

class MainViewModel(private val appRepo: AppRepository) : ViewModel() {


    fun getLoggedUserId() = appRepo.getUserId().asLiveData()
    fun getLoggedUserName() = appRepo.getUserName().asLiveData()
}