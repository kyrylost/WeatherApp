package kyrylost.apps.weatherapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CitiesItems(
    oneCityData: List<String>,
    onSelect: (String) -> Unit
) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onSelect(oneCityData[0])
        }
        .padding(10.dp)) {
        Row{
            Text(text = oneCityData[0], fontSize = 16.sp)
        }
        Row {
            Text(text = oneCityData[1] + ", ", fontSize = 12.sp)
            Text(text = oneCityData[2], fontSize = 12.sp)
        }
    }
}
