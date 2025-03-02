package com.example.jetweather.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetweather.R
import com.example.jetweather.util.formatDateTime

@Composable
fun sunsetSunrise(
    sunriseTime: Int,
    sunsetTime: Int,
    progress: Float = 0f
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.back), shape = RoundedCornerShape(14.dp))
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = R.drawable.sunrise),
                    contentDescription = "Sunrise",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White

                )
                Text(text = "Sunrise", color = Color.White, fontSize = 14.sp)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = R.drawable.sunset),
                    contentDescription = "Sunset",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
                Text(text = "Sunset", color = Color.White, fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(50)),
            color = Color.Yellow,
            trackColor = Color.Gray,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = formatDateTime(sunriseTime), color = Color.White, fontSize = 14.sp)
            Text(text = formatDateTime(sunsetTime), color = Color.White, fontSize = 14.sp)
        }
    }
}