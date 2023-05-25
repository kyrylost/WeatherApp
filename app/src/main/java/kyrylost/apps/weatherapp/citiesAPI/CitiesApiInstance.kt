package kyrylost.apps.weatherapp.citiesAPI

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CitiesApiInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.teleport.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: CitiesApiService by lazy {
        retrofit.create(CitiesApiService::class.java)
    }
}