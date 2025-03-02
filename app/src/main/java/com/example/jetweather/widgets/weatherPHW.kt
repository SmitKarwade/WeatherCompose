package com.example.jetweather.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetweather.R
import com.example.jetweather.model.WeatherData


@Composable
fun HumidityWindPressure(weatherData: WeatherData){
    val humidity = weatherData.list[0].humidity
    val wind = weatherData.list[0].speed
    val pressure = weatherData.list[0].pressure
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(colorResource(R.color.back), shape = RoundedCornerShape(14.dp))
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WeatherInfoItem(R.drawable.humidity, "$humidity %")
        WeatherInfoItem(R.drawable.wind, "$wind mph")
        WeatherInfoItem(R.drawable.weather, "$pressure hpa")
    }
}

@Composable
fun WeatherInfoItem(@DrawableRes icon : Int, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = value, color = Color.White, fontSize = 14.sp)
    }
}

@Composable
fun loadImage(imageUrl: String) {
//    AsyncImage(model = imageUrl, contentDescription = "icon")
    //Image(painter = rememberAsyncImagePainter(imageUrl), contentDescription = "icon")
    //Log.d("img", "" + imageUrl)
}