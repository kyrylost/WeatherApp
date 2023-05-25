package kyrylost.apps.weatherapp.citiesAPI

import kyrylost.apps.weatherapp.model.PossibleCitiesList
import retrofit2.http.GET
import retrofit2.http.Query

interface CitiesApiService {
    @GET("api/cities")
    suspend fun getAutocompletedCityNames(@Query("search") unfinishedCityName: String): PossibleCitiesList
}