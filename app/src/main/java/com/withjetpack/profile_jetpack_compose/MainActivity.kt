package com.withjetpack.profile_jetpack_compose
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    VehicleProfileScreen()
                }
        }
    }
}

@Composable
fun VehicleProfileScreen() {
    var isEditing by remember { mutableStateOf(false) }
    var vehicleName by remember { mutableStateOf("Car Name") }
    var vehicleType by remember { mutableStateOf("Sedan") }
    var vehicleStatus by remember { mutableStateOf("Active") }
    var vehicleSpeed by remember { mutableStateOf("60 km/h") }
    var vehicleFuel by remember { mutableStateOf("75%") }
    var userName by remember { mutableStateOf("John Doe") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = { Text("Vehicle Profile") },
            navigationIcon = {
                IconButton(onClick = { /* Handle navigation back */ }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            },
            actions = {
                if (isEditing) {
                    IconButton(onClick = { isEditing = !isEditing }) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = null)
                    }
                } else {
                    IconButton(onClick = { isEditing = !isEditing }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .background(MaterialTheme.colors.primary)
                .padding(16.dp)
                .aspectRatio(1f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        VehicleInfoField(
            label = "Vehicle Name",
            value = vehicleName,
            onValueChange = { vehicleName = it },
            isEditing = isEditing
        )

        VehicleInfoField(
            label = "Vehicle Type",
            value = vehicleType,
            onValueChange = { vehicleType = it },
            isEditing = isEditing
        )

        VehicleInfoField(
            label = "Vehicle Status",
            value = vehicleStatus,
            onValueChange = { vehicleStatus = it },
            isEditing = isEditing
        )

        VehicleInfoField(
            label = "Speed",
            value = vehicleSpeed,
            onValueChange = { vehicleSpeed = it },
            isEditing = isEditing,
            icon = Icons.Default.Edit
        )

        VehicleInfoField(
            label = "Fuel",
            value = vehicleFuel,
            onValueChange = { vehicleFuel = it },
            isEditing = isEditing,
            icon = Icons.Default.Edit
        )

        Spacer(modifier = Modifier.height(16.dp))

        // User details
        Text(
            text = "User Details",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        VehicleInfoField(
            label = "User Name",
            value = userName,
            onValueChange = { userName = it },
            isEditing = isEditing
        )

        // Add more user details fields as needed

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun VehicleInfoField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isEditing: Boolean,
    icon: ImageVector? = null
) {
    var isFocused by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .background(MaterialTheme.colors.surface)
            .padding(16.dp)
    ) {
        if (icon != null) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.padding(end = 16.dp))
        }

        Text(
            text = label,
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
            fontWeight = FontWeight.Bold
        )

            Text(
                text = value,
                modifier = Modifier.weight(2f),
                color = MaterialTheme.colors.onSurface
            )
        }

}
