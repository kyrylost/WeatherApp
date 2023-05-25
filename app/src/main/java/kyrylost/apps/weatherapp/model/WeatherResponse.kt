package kyrylost.apps.weatherapp.model

data class WeatherResponse(
    val city_name: String,
    val country_code: String,
    val data: List<WeatherData>,
    val lat: String,
    val lon: String,
    val state_code: String,
    val timezone: String
)