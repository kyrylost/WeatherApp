package kyrylost.apps.weatherapp.di

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import kyrylost.apps.weatherapp.citiesAPI.CitiesApiRepository
import kyrylost.apps.weatherapp.viewmodel.AppViewModel
import kyrylost.apps.weatherapp.weatherAPI.WeatherApiRepository

@Module
class ActivityViewModelModule {
    @Provides
    @ActivityScope
    fun provideAppViewModel(
        activity: ComponentActivity,
        weatherRepository: WeatherApiRepository,
        citiesRepository: CitiesApiRepository): AppViewModel {
        return ViewModelProvider(
            activity.viewModelStore,
            AppViewModelFactory(
                weatherRepository, citiesRepository))[AppViewModel::class.java]
    }

    @Provides
    fun provideWeatherApiRepository(): WeatherApiRepository {
        return WeatherApiRepository()
    }

    @Provides
    fun provideCitiesApiRepository(): CitiesApiRepository {
        return CitiesApiRepository()
    }
}