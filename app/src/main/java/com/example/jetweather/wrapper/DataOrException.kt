package com.example.jetweather.wrapper

class DataOrException<T, Boolean, Exception>(
    var data: T? = null,
    var e: Exception? = null,
    var loading: kotlin.Boolean = true
)
