package kyrylost.apps.weatherapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kyrylost.apps.weatherapp.citiesAPI.CitiesApiRepository
import kyrylost.apps.weatherapp.viewmodel.AppViewModel
import kyrylost.apps.weatherapp.weatherAPI.WeatherApiRepository

class AppViewModelFactory(
    private val weatherRepository: WeatherApiRepository,
    private val citiesRepository: CitiesApiRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        AppViewModel(weatherRepository, citiesRepository) as T

}