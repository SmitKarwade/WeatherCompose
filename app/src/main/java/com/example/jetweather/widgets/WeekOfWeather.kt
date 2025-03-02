package com.example.jetweather.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.jetweather.R
import com.example.jetweather.model.ActualWeather
import com.example.jetweather.util.formatDate

@Composable
fun SevenDayForecast(weatherList: List<ActualWeather>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(R.color.back), shape = RoundedCornerShape(14.dp))
            .clip(RoundedCornerShape(14.dp))

    ) {
        weatherList.forEach{ weather ->
            weekForecast(
                date = formatDate(weather.dt),
                day = "${weather.temp.day.toInt()}",
                night = "${weather.temp.night.toInt()}",
                iconEd = weather.weather.firstOrNull()?.icon ?: "01d",
                desc = weather.weather.listIterator().next().description
            )
        }
    }
}

@Composable
fun weekForecast(date : String, day : String , night : String, iconEd : String, desc : String){

    val iconUrl = "https://openweathermap.org/img/wn/$iconEd@2x.png"
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(text = date, fontSize = 16.sp, color = Color.White)

        AsyncImage(
            model = iconUrl,
            contentDescription = "Weather Icon",
            modifier = Modifier.size(40.dp)
        )

        Column(horizontalAlignment = Alignment.End) {
            Text(text = desc, fontSize = 14.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
            Text(
                text = "$day° / $night°",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}