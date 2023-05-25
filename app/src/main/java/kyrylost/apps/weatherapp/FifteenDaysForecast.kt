package kyrylost.apps.weatherapp

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kyrylost.apps.weatherapp.model.WeatherData

@Composable
fun FifteenDaysForecast(otherDaysForecast: List<WeatherData>) {
    Row(modifier = Modifier
        .horizontalScroll(rememberScrollState())
        .fillMaxWidth()
        .padding(24.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        for (oneDayWeather in otherDaysForecast){
            FifteenDaysForecastItem(oneDayWeather = oneDayWeather)
        }
    }
}