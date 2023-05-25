package kyrylost.apps.weatherapp

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.colorResource

@Composable
fun CannotGetLocationAlertDialog() {

    val openDialog = remember { mutableStateOf(true)  }
    if (openDialog.value) {

        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Cannot Get Location")
            },
            text = {
                Text("We can't get Your location." +
                        " Try to turn on geolocation on Your device," +
                        " or give us permission to check Your location in the settings.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.blue))
                ) {
                    Text("Ok", color = colorResource(id = R.color.white))
                }
            }
        )
    }
}