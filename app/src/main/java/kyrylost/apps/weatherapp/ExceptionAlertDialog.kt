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
fun ExceptionAlertDialog() {
    val openDialog = remember { mutableStateOf(true)  }
    if (openDialog.value) {

        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Cannot Get Data")
            },
            text = {
                Text("Something went wrong. Connect to the internet and try again.")
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