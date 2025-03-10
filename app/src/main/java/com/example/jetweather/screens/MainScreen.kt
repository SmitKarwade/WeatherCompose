package com.example.jetweather.screens



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetweather.R
import com.example.jetweather.model.WeatherData
import com.example.jetweather.util.formatDate
import com.example.jetweather.viewmodel.WeatherViewModel
import com.example.jetweather.widgets.HumidityWindPressure
import com.example.jetweather.widgets.SevenDayForecast
import com.example.jetweather.widgets.WeatherAppBar
import com.example.jetweather.widgets.sunsetSunrise
import com.example.jetweather.wrapper.DataOrException
import kotlin.math.roundToInt


@Composable
fun MainScreen(navController: NavController, viewModel: WeatherViewModel){

    val savedState = navController.currentBackStackEntry?.savedStateHandle

    var location by remember {
        mutableStateOf("nagpur")
    }

    LaunchedEffect(savedState?.get("location")) {
        savedState?.get<String>("location").let {
            if (it != null) {
                location = it
            }
        }
    }

    val weatherData = produceState(
        initialValue = DataOrException<WeatherData, Boolean,  Exception>(loading = true)
    ) {
        value = viewModel.getWeatherData(location)
    }.value

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()){
        when {
            weatherData.loading -> CircularProgressIndicator()
            weatherData.data != null -> {
                MainUI(weatherData.data!!, navController)
            }
            weatherData.e != null -> {
                Text(text = "Error: ${weatherData.e!!.localizedMessage}")
            }
        }
    }
}





@Composable
fun MainUI(weatherData: WeatherData, navController: NavController) {
    val backColor = colorResource(id = R.color.back)
    val backlineColor = colorResource(id = R.color.backline)

    Scaffold(topBar = {
        WeatherAppBar(weatherData.city.name,
            onAddActionClicked = {
                navController.navigate("search_screen")
            })
    }, containerColor = Color.Transparent,
        modifier = Modifier.background(Brush.verticalGradient(listOf(backColor, backlineColor)))
        ) { insidePadding ->
        Box(modifier = Modifier.fillMaxSize().padding(insidePadding)
            .background( brush = Brush.verticalGradient(listOf(backColor, backlineColor)))){
            HorizontalDivider(thickness = 1.dp, color = colorResource(R.color.backline), modifier = Modifier.fillMaxWidth())
            MainContent(weatherData)
        }
    }
}

@Composable
fun MainContent(weatherData: WeatherData) {
    val imageUrl = "https://openweathermap.org/img/wn/" + weatherData.list[0].weather.listIterator().next().icon + "@2x.png"

    LazyColumn(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(40.dp))

                Text(text = formatDate(weatherData.list[0].dt), color = Color.White, fontSize = 16.sp)

                Text(
                    text = " ${weatherData.list[0].temp.day.roundToInt()}°",
                    modifier = Modifier.padding(4.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 95.sp,
                    color = Color.White
                )

                Text(
                    text = weatherData.list[0].weather.listIterator().next().main,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(6.dp)
                )

                Text(
                    text = "Feels Like - ${weatherData.list[0].feels_like.day.toInt()}°",
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 60.dp)
                )

                HumidityWindPressure(weatherData)

                Spacer(modifier = Modifier.height(10.dp))
                sunsetSunrise(weatherData.list[0].sunrise, weatherData.list[0].sunset)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        item {
            SevenDayForecast(weatherData.list)
        }
    }
}

// Alternate
//@Composable
//fun showData(viewModel: WeatherViewModel) {
//    var weatherData by remember { mutableStateOf(DataOrException<WeatherData, Exception>(loading = true)) }
//
//    LaunchedEffect(Unit) {
//        weatherData = viewModel.getWeatherData("London")
//    }
//
//    when {
//        weatherData.loading -> CircularProgressIndicator()
//        weatherData.data != null -> Text(text = weatherData.data!!.city.name)
//        weatherData.e != null -> Text(text = "Error: ${weatherData.e!!.localizedMessage}")
//    }
//}



