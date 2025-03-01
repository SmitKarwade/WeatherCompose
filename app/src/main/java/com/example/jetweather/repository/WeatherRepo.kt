package com.example.jetweather.repository

import com.example.jetweather.model.WeatherData
import com.example.jetweather.network.WeatherAPI
import com.example.jetweather.wrapper.DataOrException
import javax.inject.Inject

class WeatherRepo @Inject constructor(val weatherapi: WeatherAPI){

    suspend fun getWeatherInfo(city: String): DataOrException<WeatherData, Boolean, Exception> {
        val result = DataOrException<WeatherData, Boolean,  Exception>()
        try {
            result.data = weatherapi.getWeather(query = city)
            result.loading = false
        } catch (e: Exception) {
            result.e = e
            result.loading = false
        }
        return result
    }


}