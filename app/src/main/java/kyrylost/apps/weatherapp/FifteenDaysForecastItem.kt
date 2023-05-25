package kyrylost.apps.weatherapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kyrylost.apps.weatherapp.model.WeatherData

@Composable
fun FifteenDaysForecastItem(oneDayWeather: WeatherData) {
    Column (modifier = Modifier
        .clip(shape = RoundedCornerShape(24.dp))
        .background(colorResource(R.color.blue))
    ){
        Text(modifier = Modifier
            .padding(top = 24.dp, start = 24.dp, bottom = 6.dp, end = 24.dp),
            text = oneDayWeather.datetime,
            fontSize = 12.sp,
            color = Color.White
        )
        Text(modifier = Modifier
                .padding(start = 24.dp, top=6.dp, end = 24.dp),
            text = oneDayWeather.weather.description,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(modifier = Modifier
                .padding(start = 24.dp, top=6.dp, bottom = 24.dp, end = 24.dp),
            text = "${oneDayWeather.high_temp}° / ${oneDayWeather.low_temp}°",
            fontSize = 12.sp,
            color = Color.White
        )
    }
}