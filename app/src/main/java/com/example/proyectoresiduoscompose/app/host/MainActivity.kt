package com.example.proyectoresiduoscompose.app.host

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectoresiduoscompose.R
import com.example.proyectoresiduoscompose.presentation.ui.editResidueScreen.EditResidueScreen
import com.example.proyectoresiduoscompose.presentation.theme.ProyectoResiduosComposeTheme
import com.example.proyectoresiduoscompose.presentation.ui.homeScreen.HomeScreen
import com.example.proyectoresiduoscompose.presentation.ui.routeCollectionScreen.RouteCollectionScreen
import com.example.proyectoresiduoscompose.presentation.ui.routeInfoScreen.RouteInfoScreen


val PoppinsFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Light)
    //habería que meter as outras que vou usar
)

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    }
                }
            }
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
    ),
    BottomNavItem(
        title = "Config",
        route = "RouteDestinationsScreen",
        selectedIcon = Icons.Rounded.Settings,
        unselectedIcon = Icons.Rounded.Settings
    )
)




