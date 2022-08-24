package com.sanjeet.weatherinfo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sanjeet.weatherinfo.repository.WeatherRepository
import java.lang.IllegalArgumentException

class WeatherViewModelFactory constructor(private val repository: WeatherRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(WeatherViewModel::class.java)){
            WeatherViewModel(this.repository) as T
        }else{
            throw IllegalArgumentException("ViewModel is not found")
        }
    }
}