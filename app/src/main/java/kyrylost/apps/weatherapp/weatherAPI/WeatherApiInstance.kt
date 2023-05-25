package kyrylost.apps.weatherapp.weatherAPI

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.weatherbit.io/v2.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}