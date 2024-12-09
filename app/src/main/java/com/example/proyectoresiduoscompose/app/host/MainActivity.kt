package com.example.proyectoresiduoscompose.app.host

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.proyectoresiduoscompose.R
import com.example.proyectoresiduoscompose.data.database.ReOilDatabase
import com.example.proyectoresiduoscompose.data.entity.AddressEntity
import com.example.proyectoresiduoscompose.data.entity.ClientEntity
import com.example.proyectoresiduoscompose.data.entity.RouteEntity
import com.example.proyectoresiduoscompose.data.entity.TruckEntity
import com.example.proyectoresiduoscompose.data.entity.TruckerEntity
import com.example.proyectoresiduoscompose.data.utils.SharedPreferencesLogin
import com.example.proyectoresiduoscompose.presentation.ui.editResidueScreen.EditResidueScreen
import com.example.proyectoresiduoscompose.presentation.theme.ProyectoResiduosComposeTheme
import com.example.proyectoresiduoscompose.presentation.ui.signatureScreen.SignatureScreen
import com.example.proyectoresiduoscompose.presentation.ui.homeScreen.HomeScreen
import com.example.proyectoresiduoscompose.presentation.ui.homeScreen.HomeViewModel
import com.example.proyectoresiduoscompose.presentation.ui.routeCollectionScreen.RouteCollectionScreen
import com.example.proyectoresiduoscompose.presentation.ui.routeInfoScreen.RouteInfoScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


val PoppinsFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Light)
    //habería que meter as outras que vou usar
)

class MainActivity : ComponentActivity() {

    private val reOilDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            ReOilDatabase::class.java,
            "ReOil"
        ).build()
    }

    private val viewmodel by viewModels<HomeViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HomeViewModel(reOilDatabase.routeDao()) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        simulateLogIn() //I simulate that the user logged in

        //uncomment this to fill database with test data
        populateDatabase()

        viewmodel.executeSomething()

        setContent {
            ProyectoResiduosComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController=navController, startDestination = "HomeScreen"){
                        composable("HomeScreen"){
                            HomeScreen(
                                navController=navController
                            )
                        }

                        composable("RouteInfoScreen"){
                            RouteInfoScreen(
                                navController=navController
                            )
                        }

                        composable("RouteCollectionScreen"){
                            RouteCollectionScreen(
                                navController=navController
                            )
                        }

                        composable("EditResidueScreen"){
                            EditResidueScreen(
                                navController=navController
                            )
                        }

                        composable("SignatureScreen"){
                            SignatureScreen(
                                navController=navController
                            )
                        }
                    }
                }
            }
        }
    }

    private fun simulateLogIn(){
        SharedPreferencesLogin.clearUser(this)

        if (SharedPreferencesLogin.getUserId(this)!=-1){
            //get the user from database and save it in the state
        }else{
            //i would redirect to the login screen, but no tiempo :)
            SharedPreferencesLogin.saveUser(this,1)
            Toast.makeText(this, "Logued in as Juana", Toast.LENGTH_LONG).show()
        }
    }

    private fun populateDatabase() {
        lifecycleScope.launch(Dispatchers.IO) {
            reOilDatabase.clearAllTables()

            // Create and insert 8 trucks with real-world truck data
            val trucks = listOf(
                TruckEntity(plate = "AB123CD", name = "Volvo FH16 750"),
                TruckEntity(plate = "BC234DE", name = "Scania R Series"),
                TruckEntity(plate = "CD345EF", name = "MAN TGX 18.640"),
                TruckEntity(plate = "DE456FG", name = "Mercedes-Benz Actros"),
                TruckEntity(plate = "EF567GH", name = "DAF XF 105"),
                TruckEntity(plate = "FG678HI", name = "Iveco Stralis"),
                TruckEntity(plate = "GH789IJ", name = "Renault Trucks T High"),
                TruckEntity(plate = "HI890JK", name = "Mercedes-Benz Arocs")
            )
            val truckIds = reOilDatabase.truckDao().insertTrucks(trucks)

            // Create and insert 8 truckers with real names and details
            val truckers = listOf(
                TruckerEntity(dni = "12345678A", name = "John", lastName1 = "Doe", lastName2 = "Smith", active = true, truckId = truckIds[0].toInt()),
                TruckerEntity(dni = "23456789B", name = "Anna", lastName1 = "Johnson", lastName2 = "Taylor", active = true, truckId = truckIds[1].toInt()),
                TruckerEntity(dni = "34567890C", name = "Carlos", lastName1 = "Garcia", lastName2 = "Rodriguez", active = true, truckId = truckIds[2].toInt()),
                TruckerEntity(dni = "45678901D", name = "Eva", lastName1 = "Martinez", lastName2 = "Perez", active = true, truckId = truckIds[3].toInt()),
                TruckerEntity(dni = "56789012E", name = "Paul", lastName1 = "Miller", lastName2 = "Wilson", active = true, truckId = truckIds[4].toInt()),
                TruckerEntity(dni = "67890123F", name = "Sophia", lastName1 = "Davis", lastName2 = "Lopez", active = true, truckId = truckIds[5].toInt()),
                TruckerEntity(dni = "78901234G", name = "William", lastName1 = "Gonzalez", lastName2 = "Martinez", active = true, truckId = truckIds[6].toInt()),
                TruckerEntity(dni = "89012345H", name = "Lucas", lastName1 = "Hernandez", lastName2 = "Garcia", active = true, truckId = truckIds[7].toInt())
            )
            reOilDatabase.truckerDao().insertTruckers(truckers)

            // Create and insert 8 routes with real-world route information
            val routes = listOf(
                RouteEntity(name = "Route 101 - California to Nevada", state = "Active", startDate = System.currentTimeMillis(), endDate = null, truckId = truckIds[0].toInt()),
                RouteEntity(name = "Route 202 - Texas to Florida", state = "Active", startDate = System.currentTimeMillis(), endDate = null, truckId = truckIds[1].toInt()),
                RouteEntity(name = "Route 303 - New York to Boston", state = "Completed", startDate = System.currentTimeMillis() - 86400000, endDate = System.currentTimeMillis(), truckId = truckIds[2].toInt()),
                RouteEntity(name = "Route 404 - Miami to Chicago", state = "Active", startDate = System.currentTimeMillis(), endDate = null, truckId = truckIds[3].toInt()),
                RouteEntity(name = "Route 505 - Arizona to California", state = "Active", startDate = System.currentTimeMillis(), endDate = null, truckId = truckIds[4].toInt()),
                RouteEntity(name = "Route 606 - Colorado to Nevada", state = "Completed", startDate = System.currentTimeMillis() - 86400000, endDate = System.currentTimeMillis(), truckId = truckIds[5].toInt()),
                RouteEntity(name = "Route 707 - Oregon to Washington", state = "Active", startDate = System.currentTimeMillis(), endDate = null, truckId = truckIds[6].toInt()),
                RouteEntity(name = "Route 808 - Utah to Idaho", state = "Completed", startDate = System.currentTimeMillis() - 86400000, endDate = System.currentTimeMillis(), truckId = truckIds[7].toInt())
            )
            reOilDatabase.routeDao().insertAll(routes)

            // Create and insert 8 addresses (realistic addresses for clients)
            val addresses = listOf(
                AddressEntity(addressText = "1234 Elm St, Springfield, IL", longitude = 123456, latitude = 654321),
                AddressEntity(addressText = "5678 Oak Rd, Shelbyville, KY", longitude = 123457, latitude = 654322),
                AddressEntity(addressText = "9101 Maple Ave, Chicago, IL", longitude = 123458, latitude = 654323),
                AddressEntity(addressText = "1122 Birch Blvd, Orlando, FL", longitude = 123459, latitude = 654324),
                AddressEntity(addressText = "3344 Pine Dr, Miami, FL", longitude = 123460, latitude = 654325),
                AddressEntity(addressText = "5566 Cedar Ln, Dallas, TX", longitude = 123461, latitude = 654326),
                AddressEntity(addressText = "7788 Redwood St, Austin, TX", longitude = 123462, latitude = 654327),
                AddressEntity(addressText = "9900 Fir Ave, Denver, CO", longitude = 123463, latitude = 654328)
            )
            val addressIds = reOilDatabase.addressDao().insertAddresses(addresses)

            // Create and insert 8 clients with real details
            val clients = listOf(
                ClientEntity(nnss = 123456789, name = "Acme Logistics", addressId = addressIds[0].toInt()),
                ClientEntity(nnss = 987654321, name = "Global Freight", addressId = addressIds[1].toInt()),
                ClientEntity(nnss = 112233445, name = "Redwood Transport", addressId = addressIds[2].toInt()),
                ClientEntity(nnss = 223344556, name = "Mile High Movers", addressId = addressIds[3].toInt()),
                ClientEntity(nnss = 334455667, name = "Sunshine Express", addressId = addressIds[4].toInt()),
                ClientEntity(nnss = 445566778, name = "Blue Sky Logistics", addressId = addressIds[5].toInt()),
                ClientEntity(nnss = 556677889, name = "Star Freight", addressId = addressIds[6].toInt()),
                ClientEntity(nnss = 667788990, name = "Clear Path Transport", addressId = addressIds[7].toInt())
            )
            reOilDatabase.clientDao().insertAll(clients)
        }
    }
}


//mais ou menos como facer  navegacion

data class BottomNavItem(
    val title : String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route = "HomeScreen",
        selectedIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.Rounded.Home
    )
)







