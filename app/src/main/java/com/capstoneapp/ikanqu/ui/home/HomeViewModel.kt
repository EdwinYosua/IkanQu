package com.capstoneapp.ikanqu.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneapp.ikanqu.repository.AppRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val appRepo: AppRepository) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            appRepo.clearLoginData()
        }
    }
}