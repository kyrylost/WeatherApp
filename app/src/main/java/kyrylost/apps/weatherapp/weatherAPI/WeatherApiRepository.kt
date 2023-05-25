package kyrylost.apps.weatherapp.weatherAPI

import kyrylost.apps.weatherapp.model.WeatherResponse

class WeatherApiRepository {
    suspend fun getForecastByCityName(cityName: String) : WeatherResponse =
        WeatherApiInstance.apiService.getForecastByCityName(cityName)

    suspend fun getForecastByCityCords(latitude: String,longitude: String) : WeatherResponse =
        WeatherApiInstance.apiService.getForecastByCityCords(latitude, longitude)
}