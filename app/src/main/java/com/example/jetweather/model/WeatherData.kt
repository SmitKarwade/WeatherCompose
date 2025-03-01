package com.example.jetweather.model

data class WeatherData(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ActualWeather>,
    val message: Double
)