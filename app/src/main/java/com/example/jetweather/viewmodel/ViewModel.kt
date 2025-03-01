package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import com.example.jetweather.model.WeatherData
import com.example.jetweather.repository.WeatherRepo
import com.example.jetweather.wrapper.DataOrException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepo) : ViewModel() {
    suspend fun getWeatherData(city: String): DataOrException<WeatherData, Boolean, Exception> {
        return repository.getWeatherInfo(city)
    }
}