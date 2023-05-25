package kyrylost.apps.weatherapp.citiesAPI

import kyrylost.apps.weatherapp.model.PossibleCitiesList

class CitiesApiRepository{
    suspend fun getAutocompletedCityNames(unfinishedCityName: String) : PossibleCitiesList =
        CitiesApiInstance.apiService.getAutocompletedCityNames(unfinishedCityName)
}