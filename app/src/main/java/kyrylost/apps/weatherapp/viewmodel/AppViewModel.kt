package kyrylost.apps.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kyrylost.apps.weatherapp.citiesAPI.CitiesApiRepository
import kyrylost.apps.weatherapp.model.WeatherResponse
import kyrylost.apps.weatherapp.weatherAPI.WeatherApiRepository
import javax.inject.Inject

class AppViewModel @Inject constructor(
    private val weatherRepository: WeatherApiRepository,
    private val citiesRepository: CitiesApiRepository,
) : ViewModel() {

    var citySearchLiveData = MutableLiveData<List<List<String>>>()
    var cityWeatherLiveData = MutableLiveData<WeatherResponse>()

    var currentCityName = MutableLiveData<String>()

    var cannotGetLocation = MutableLiveData<Boolean>()

    var cannotGetData = MutableLiveData<Boolean>()

    fun getForecastByCityName(cityName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val weatherResponse = weatherRepository.getForecastByCityName(cityName)

                cityWeatherLiveData.postValue(weatherResponse)

                weatherResponse.apply {
                    currentCityName.postValue(city_name)
                }
            } catch (e: Exception) {
                cannotGetData.postValue(true)
                Log.e("getForecastByCityName", e.toString())
            }

        }
    }

    fun getForecastByLongitudeAndLatitude(latitude: Double, longitude: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val weatherResponse = weatherRepository.getForecastByCityCords(
                    latitude.toString(), longitude.toString()
                )

                cityWeatherLiveData.postValue(weatherResponse)

                weatherResponse.apply {
                    currentCityName.postValue(city_name)
                }
            } catch (e: Exception) {
                cannotGetData.postValue(true)
                Log.e("getForecastByLongitudeAndLatitude", e.toString())
            }

        }
    }

    fun getAutocompletedCityNames(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                citiesRepository.getAutocompletedCityNames(name).apply {
                    val matchedUkrainianCities = mutableListOf<List<String>>()

                    val citySearchResults = _embedded.citySearchResults
                    for (cityData in citySearchResults) {
                        val matchingFullNameList = cityData.matching_full_name.split(", ")
                        if (matchingFullNameList[2] == "Ukraine")
                            matchedUkrainianCities.add(matchingFullNameList)
                    }
                    citySearchLiveData.postValue(matchedUkrainianCities as List<List<String>>)
                }
            } catch (e: Exception) {
                cannotGetData.postValue(true)
                Log.e("getAutocompletedCityNames", e.toString())
            }

        }
    }
}