package com.example.jetweather.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetweather.R
import com.example.jetweather.navigation.AppNavigation
import com.example.jetweather.ui.theme.JetWeatherTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherApp()
        }
    }
}


@Composable
fun WeatherApp(){

    val backColor = colorResource(id = R.color.back)
    val backlineColor = colorResource(id = R.color.backline)
    JetWeatherTheme {
        Surface(modifier = Modifier.fillMaxSize()){
            Box(modifier = Modifier.fillMaxSize()
                .background(brush = Brush.verticalGradient(listOf(backColor, backlineColor)))){

                AppNavigation()
            }
        }
    }
}