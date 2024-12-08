package com.example.proyectoresiduoscompose.presentation.ui.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoresiduoscompose.data.dao.RouteDao
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dao: RouteDao
) :ViewModel(){

    fun executeSomething(){
        viewModelScope.launch {
            val truck = dao.getTruckWithRoutes(1)
        }
    }
}