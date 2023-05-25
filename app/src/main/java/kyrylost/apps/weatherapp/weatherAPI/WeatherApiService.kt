package kyrylost.apps.weatherapp.weatherAPI

import kyrylost.apps.weatherapp.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("forecast/daily?days=16&key=3561b82e4d5248f89bfebfd2c9c5980a")
    suspend fun getForecastByCityName(@Query("city") cityName: String): WeatherResponse

    @GET("forecast/daily?days=16&key=3561b82e4d5248f89bfebfd2c9c5980a")
    suspend fun getForecastByCityCords(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
    ): WeatherResponse
}