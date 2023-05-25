package kyrylost.apps.weatherapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import kyrylost.apps.weatherapp.viewmodel.AppViewModel

@Composable
fun Main(viewModel: AppViewModel) {
    val currentCityName by viewModel.currentCityName.observeAsState("Undefined")

    val matchedCities by viewModel.citySearchLiveData.observeAsState()
    val currentCityWeather by viewModel.cityWeatherLiveData.observeAsState()

    val cannotGetLocation by viewModel.cannotGetLocation.observeAsState(false)
    val cannotGetData by viewModel.cannotGetData.observeAsState(false)


    if (cannotGetLocation == true) {
        CannotGetLocationAlertDialog()
    }

    if (cannotGetData == true) {
        ExceptionAlertDialog()
    }


    var city by remember {
        mutableStateOf("")
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier
        .fillMaxSize()
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 24.dp, end = 24.dp)
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },

                    shape = RoundedCornerShape(24.dp),
                    value = city,
                    onValueChange = {
                        city = it
                        expanded = true
                    },
                    trailingIcon = {
                        IconButton(onClick = { expanded = false }) {
                            Icon(Icons.Filled.Close,
                                "Close drop down list",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = colorResource(R.color.blue),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.White
                    ),
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp
                    ),
                    singleLine = true,
                )
            }

            AnimatedVisibility(visible = expanded) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 48.dp)
                        .width(textFieldSize.width.dp),
                    elevation = 15.dp,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 24.dp,
                        bottomEnd = 24.dp)
                ) {

                    LazyColumn(
                        modifier = Modifier.heightIn(max = 200.dp),
                    ) {

                        if (city.isNotEmpty()) {
                            viewModel.getAutocompletedCityNames(city)
                            if (matchedCities != null) {
                                items(
                                    matchedCities!!
                                ) {
                                    CitiesItems(oneCityData = it) { cityName ->
                                        viewModel.getForecastByCityName(cityName)
                                        city = cityName
                                        expanded = false
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (currentCityWeather != null) {
            val todayWeather = currentCityWeather!!.data[0]
            val otherDaysForecast = currentCityWeather!!.data.subList(1, 16)

            val windDirection = when {
                todayWeather.wind_dir > 337.5 || todayWeather.wind_dir <= 22.5 -> "North"
                todayWeather.wind_dir > 22.5 && todayWeather.wind_dir <= 67.5 -> "North-East"
                todayWeather.wind_dir > 67.5 && todayWeather.wind_dir <= 112.5 -> "East"
                todayWeather.wind_dir > 112.5 && todayWeather.wind_dir <= 157.5 -> "South-East"
                todayWeather.wind_dir > 157.5 && todayWeather.wind_dir <= 202.5 -> "South"
                todayWeather.wind_dir > 202.5 && todayWeather.wind_dir <= 247.5 -> "South-West"
                todayWeather.wind_dir > 247.5 && todayWeather.wind_dir <= 292.5 -> "West"
                todayWeather.wind_dir > 292.5 && todayWeather.wind_dir <= 337.5 -> "North-West"
                else -> "Unknown"
            }

            TodayForecast(todayWeather, windDirection, currentCityName)
            FifteenDaysForecast(otherDaysForecast)

        }
    }
}