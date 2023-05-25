package kyrylost.apps.weatherapp.di

import androidx.activity.ComponentActivity
import kyrylost.apps.weatherapp.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityViewModelModule::class])
interface ActivityViewModelComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun componentActivity(activity: ComponentActivity): Builder
        fun build(): ActivityViewModelComponent
    }

    fun inject(activity: MainActivity)
}