package com.example.jetweather.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    JetWeatherTheme {
        Surface(modifier = Modifier.fillMaxSize(),
            color = Color(0xEB313541)
        ) {
            AppNavigation()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetWeatherTheme {
    }
}