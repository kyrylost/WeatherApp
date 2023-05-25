package kyrylost.apps.weatherapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun TodayForecast(todayWeather: WeatherData, windDirection: String, currentCityName: String) {

    val textRowPadding = Modifier
        .padding(start = 24.dp, top=6.dp, end = 24.dp)
    val bottomTextRowPadding = Modifier
        .padding(start = 24.dp, top=6.dp, bottom = 24.dp, end = 24.dp)

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 24.dp, start = 24.dp, end = 24.dp)
        .clip(shape = RoundedCornerShape(24.dp))
        .background(colorResource(R.color.blue))
    ) {
        Text(
            modifier = Modifier
                .padding(top = 24.dp, start = 24.dp),
            text = currentCityName,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Text(
            modifier = Modifier
                .padding(start = 24.dp),
            text = todayWeather.datetime,
            fontSize = 12.sp,
            color = Color.White
        )

        Text(
            modifier = Modifier
                .padding(top = 24.dp, start = 24.dp, bottom = 6.dp),
            text = todayWeather.weather.description,
            fontWeight = FontWeight.Bold,
            color = Color.White)

        Row (modifier = textRowPadding){
            Text(text = "Temperature: ", fontSize = 12.sp, color = Color.White)
            Text(text = "${todayWeather.high_temp}° / ${todayWeather.low_temp}°", fontSize = 12.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }
        Row (modifier = textRowPadding){
            Text(text = "Feels Like : ", fontSize = 12.sp, color = Color.White)
            Text(text = "${todayWeather.app_max_temp}° / ${todayWeather.app_min_temp}°", fontSize = 12.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }
        Row (modifier = bottomTextRowPadding){
            Text(text = "Wind: ", fontSize = 12.sp, color = Color.White)
            Text(text = "$windDirection | ${todayWeather.wind_spd} | ${todayWeather.wind_gust_spd}", fontSize = 12.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}