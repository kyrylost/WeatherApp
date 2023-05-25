package kyrylost.apps.weatherapp.di

import dagger.Component

@Component
interface MainComponent {
    fun activityViewModelComponentBuilder():
            ActivityViewModelComponent.Builder
}