package kyrylost.apps.weatherapp

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kyrylost.apps.weatherapp.MainApplication.Companion.mainComponent
import kyrylost.apps.weatherapp.di.DaggerMainComponent
import kyrylost.apps.weatherapp.di.MainComponent
import kyrylost.apps.weatherapp.ui.theme.WeatherAppTheme
import kyrylost.apps.weatherapp.viewmodel.AppViewModel
import javax.inject.Inject

class MainApplication : Application() {
    companion object {
        val mainComponent: MainComponent by lazy {
            DaggerMainComponent.create()
        }
    }
}


class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: AppViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainComponent.activityViewModelComponentBuilder()
            .componentActivity(this).build().inject(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {
            WeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(R.color.light_blue)
                ) {
                    Main(viewModel)
                }
            }
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission()
            return
        }
        else {
            getLastKnownLocation()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            getLastKnownLocation()
        } else {
            viewModel.getForecastByCityName("Kyiv")
            viewModel.cannotGetLocation.value = true
            Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
        }
    }

    private fun requestLocationPermission() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun getLastKnownLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location->
                if (location != null) {
                    viewModel.getForecastByLongitudeAndLatitude(location.latitude, location.longitude)
                }
                else {
                    viewModel.getForecastByCityName("Kyiv")
                    viewModel.cannotGetLocation.value = true
                }

            }
    }

}