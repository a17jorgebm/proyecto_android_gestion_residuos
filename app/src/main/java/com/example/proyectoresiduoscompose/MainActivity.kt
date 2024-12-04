package com.example.proyectoresiduoscompose

import android.graphics.drawable.Icon
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectoresiduoscompose.ui.theme.ProyectoResiduosComposeTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


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

                        composable("RouteDestinationsScreen"){
                            RouteDestinationsScreen(
                                navController=navController
                            )
                        }

                        composable("DestinationScreen"){
                            DestinationScreen(
                                navController=navController
                            )
                        }

                        composable("ItemInDestinationScreen"){
                            ItemInDestinationScreen(
                                navController=navController
                            )
                        }
                    }
                }
            }
        }
    }
}

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

@Composable
fun HomeScreen(
    navController: NavController
){
    var selectedBottomNavItem by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        containerColor = Color.Yellow, //para que o menu estea flotando
        topBar = {
            Row(
                Modifier
                    .background(Color(0xFF1D1D1D))
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.perfil),
                            contentDescription = "Foto de perfil",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1f)
                                .clip(CircleShape)
                        )
                        Text(
                            text = "Juana",
                            color = Color.White
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxHeight()
                            .aspectRatio(1f)
                            .background(Color(0xFF292928))
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ){
                        IconButton(
                            onClick = {  }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.campana),
                                contentDescription = "Notifications",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF292928),
                modifier = Modifier
                    .background(Color(0xFF292928)),

            ) {
                bottomNavItems.forEachIndexed{i, item ->
                    NavigationBarItem(
                        selected = i==selectedBottomNavItem,
                        onClick = {
                            selectedBottomNavItem=i
                            navController.navigate(item.route)
                        },
                        icon = {
                            Icon(
                                imageVector = item.unselectedIcon,
                                contentDescription = item.title,
                                tint = Color.White,
                                modifier = Modifier
                                    .height(30.dp)
                                    .aspectRatio(1f)
                            )
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF1D1D1D))
                .padding(16.dp)
            ,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Row(
                modifier = Modifier
                    //.padding(16.dp) //margen (ao final fixeno co contedor principal)
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF292928))
                    .clickable {
                        navController.navigate("RouteDestinationsScreen")
                    }
                    //.padding(0.dp) //padding interior (0 para que a imagen ocupe todo)
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.4f)
                        .padding(16.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Trayecto de hoy",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.poppins_regular, FontWeight.Light))
                    )
                    Text(
                        text = "Ver en el mapa",
                        color = Color(0xFFD8FF7E),
                        fontSize = 12.sp
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.ruta),
                    contentDescription = "Imagen sección trayecto de hoy",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .weight(0.6f)
                )
            }

            Row(
                modifier = Modifier
                    //.padding(16.dp) //margen (ao final fixeno co contedor principal)
                    .fillMaxWidth()
                    .height(120.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
                //.padding(0.dp) //padding interior (0 para que a imagen ocupe todo)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFD8FF7E))
                        .weight(0.5f)
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Tu camión",
                                fontSize = 16.sp,
                                color = Color.Black,
                                fontFamily = FontFamily(Font(R.font.poppins_regular, FontWeight.Light))
                            )
                            Text(
                                text = "Ver",
                                color = Color(0xFF7F7F7F), //gris clarito
                                fontSize = 12.sp
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.camion),
                            contentDescription = "Trayectos",
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .height(60.dp)
                                .offset(x = 5.dp, y = 5.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .height(120.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF292928))
                        .weight(0.5f)
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Trayectos",
                                fontSize = 16.sp,
                                color = Color.White,
                                fontFamily = FontFamily(Font(R.font.poppins_regular, FontWeight.Light))
                            )
                            Text(
                                text = "Ver",
                                color = Color(0xFF7F7F7F), //gris clarito
                                fontSize = 12.sp
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.carretera),
                            contentDescription = "Trayectos",
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .height(60.dp)
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun RouteDestinationsScreen(
    navController: NavController
){
    var selectedBottomNavItem by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        containerColor = Color.Yellow, //para que o menu estea flotando
        topBar = {
            Row(
                Modifier
                    .background(Color(0xFF1D1D1D))
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                                contentDescription = "Atrás",
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .aspectRatio(1f),
                                tint = Color.White
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.perfil),
                            contentDescription = "Foto de perfil",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1f)
                                .clip(CircleShape)
                        )
                        Text(
                            text = "Juana",
                            color = Color.White
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxHeight()
                            .aspectRatio(1f)
                            .background(Color(0xFF292928))
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ){
                        IconButton(
                            onClick = {  }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.campana),
                                contentDescription = "Notifications",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF292928),
                modifier = Modifier
                    .background(Color(0xFF292928))
            ) {
                bottomNavItems.forEachIndexed{i, item ->
                    NavigationBarItem(
                        selected = i==selectedBottomNavItem,
                        onClick = {
                            selectedBottomNavItem=i
                            navController.navigate(item.route)
                        },
                        icon = {
                            Icon(
                                imageVector = item.unselectedIcon,
                                contentDescription = item.title,
                                tint = Color.White
                            )
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF1D1D1D))
                .padding(16.dp, 16.dp, 16.dp, 0.dp)
                .verticalScroll(rememberScrollState())
            ,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Jueves 17",
                        color = Color.White
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.lorry),
                            contentDescription = "Camión",
                            modifier = Modifier
                                .height(25.dp)
                                .aspectRatio(1f),
                            tint = Color.White
                        )
                        Text(
                            text = "0628BNY",
                            color = Color.White
                        )
                    }

                }
            }
            Row(
                modifier = Modifier
                    //.padding(16.dp) //margen (ao final fixeno co contedor principal)
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        navController.navigate("RouteDestinationsScreen")
                    }
                //.padding(0.dp) //padding interior (0 para que a imagen ocupe todo)
            ) {
                val singapore = LatLng(1.35, 103.87)
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(singapore, 10f)
                }
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState
                ){
                    Marker(
                        state = MarkerState(position = singapore),
                        title = "Singapore",
                        snippet = "Marker in Singapore"
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "7 clientes",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(0.dp,0.dp,0.dp,16.dp)
            )
            {
                repeat(7){
                    Row(
                        modifier = Modifier.height(140.dp)
                    ){
                        //indicador de estado
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(0.dp, 0.dp, 10.dp, 0.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFD8FF7E))
                                    .height(15.dp)
                                    .aspectRatio(1f)
                                    .align(Alignment.TopCenter)
                                    .padding(0.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight() // 140+16+15 (heightContenedor+gap+padding)
                                    .width(3.dp)
                                    .offset(0.dp, 35.dp) //15+20
                                    .align(Alignment.TopCenter)
                                    .background(Color(0xFFD8FF7E))
                            )
                        }

                        //información de la recogida
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .padding(0.dp)
                                .background(Color(0xFF292928))
                                .padding(16.dp)
                                .weight(1f)
                                .clickable {
                                    navController.navigate("DestinationScreen")
                                }
                        ){
                            Column(
                                verticalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxHeight()
                            ) {
                                Text(
                                    text = "Talleres Juan Antornio SL Sociedad anonima",
                                    color = Color.White
                                )
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.eye),
                                            contentDescription = "Observations",
                                            modifier = Modifier.height(20.dp),
                                            tint = Color(0xC6FFFFFF)
                                        )
                                        Text(
                                            text = "Ir entre las 10 y las 12",
                                            color = Color(0xC6FFFFFF),
                                            fontSize = 12.sp
                                        )
                                    }
                                    Icon(
                                        painter = painterResource(id = R.drawable.bidon_aceite),
                                        contentDescription = "Aceite usado",
                                        tint = Color(0xC6FFFFFF),
                                        modifier = Modifier
                                            .height(20.dp)
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .padding(0.dp)
                                    .background(Color(0xFFD8FF7E))
                                    .padding(30.dp, 5.dp)
                                    .align(Alignment.BottomEnd)
                                ,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Ver",
                                    color = Color.Black,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier.height(140.dp)
                ){
                    //indicador de estado
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(0.dp, 0.dp, 10.dp, 0.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(0.dp, 20.dp, 0.dp, 0.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF7F7F7F))
                                .height(15.dp)
                                .aspectRatio(1f)
                                .align(Alignment.TopCenter)
                                .padding(0.dp)
                        )
                    }

                    //información de la recogida
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .padding(0.dp)
                            .background(Color(0xFF292928))
                            .padding(16.dp)
                            .weight(1f)
                    ){
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxHeight()
                        ) {
                            Text(
                                text = "Talleres Juan Antornio SL Sociedad anonima",
                                color = Color.White
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.eye),
                                        contentDescription = "Observations",
                                        modifier = Modifier.height(20.dp),
                                        tint = Color.White
                                    )
                                    Text(
                                        text = "Ir entre las 10 y las 12",
                                        color = Color.White,
                                        fontSize = 12.sp
                                    )
                                }
                                Icon(
                                    painter = painterResource(id = R.drawable.bidon_aceite),
                                    contentDescription = "Aceite usado",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .height(20.dp)
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .padding(0.dp)
                                .background(Color(0xFFD8FF7E))
                                .padding(30.dp, 5.dp)
                                .align(Alignment.BottomEnd)
                            ,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Ver",
                                color = Color.Black,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun DestinationScreen(
    navController: NavController
){
    var selectedBottomNavItem by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        containerColor = Color.Yellow, //para que o menu estea flotando
        topBar = {
            Row(
                Modifier
                    .background(Color(0xFF1D1D1D))
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                                contentDescription = "Atrás",
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .aspectRatio(1f),
                                tint = Color.White
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.perfil),
                            contentDescription = "Foto de perfil",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1f)
                                .clip(CircleShape)
                        )
                        Text(
                            text = "Juana",
                            color = Color.White
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxHeight()
                            .aspectRatio(1f)
                            .background(Color(0xFF292928))
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ){
                        IconButton(
                            onClick = {  }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.campana),
                                contentDescription = "Notifications",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF292928),
                modifier = Modifier
                    .background(Color(0xFF292928))
            ) {
                bottomNavItems.forEachIndexed{i, item ->
                    NavigationBarItem(
                        selected = i==selectedBottomNavItem,
                        onClick = {
                            selectedBottomNavItem=i
                            navController.navigate(item.route)
                        },
                        icon = {
                            Icon(
                                imageVector = item.unselectedIcon,
                                contentDescription = item.title,
                                tint = Color.White
                            )
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF1D1D1D))
                .padding(16.dp, 16.dp, 16.dp, 0.dp)
                .verticalScroll(rememberScrollState())
            ,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "Talleres Juan Antornio SL Sociedad anonima",
                        color = Color.White,
                        modifier = Modifier
                            .weight(1f)
                    )
                    Box(
                        modifier = Modifier
                            .padding(0.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xFFD8FF7E))
                            .padding(15.dp, 10.dp)
                            .clickable {
                                //cousa que facer
                            },
                        contentAlignment = Alignment.Center
                    ){
                        Text("Ver cliente")
                    }
                }
                Text(
                    text = "Información del productor",
                    fontSize = 14.sp,
                    color = Color(0xC6FFFFFF)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = "Ubicación",
                        tint = Color.White,
                    )
                    Text(
                        text = "Rúa Antonio Amigo, 8, 15860 Santa Comba, A Coruña",
                        modifier = Modifier
                            .weight(1f),
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(15.dp,15.dp,15.dp,40.dp)
            ) {
                Text(
                    text = "Residuos a recoger",
                    fontSize = 14.sp,
                    color = Color(0xC6FFFFFF),
                    modifier = Modifier
                        .padding(0.dp,0.dp,0.dp,10.dp)
                )

                // residuos
                Column{
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFF292928))
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(15.dp)
                            .clickable {
                                navController.navigate("ItemInDestinationScreen")
                            }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.bidon_aceite),
                            contentDescription = "Oil",
                            modifier = Modifier
                                .fillMaxHeight(),
                            tint = Color(0xFF7F7F7F)
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(15.dp, 0.dp, 0.dp, 0.dp)
                                .fillMaxHeight()
                            ,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Text(
                                text = "Aceite usado",
                                fontSize = 14.sp,
                                color = Color.White
                            )
                            Text(
                                text = "Cantidad: 900l",
                                fontSize = 14.sp,
                                color = Color(0xC6FFFFFF)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .padding(0.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color(0xFF7F7F7F))
                                .padding(25.dp, 0.dp)
                            ,
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text = "Ver",
                                fontSize = 14.sp
                            )
                        }
                    }
                }

                //añadir residuo
                Row(
                    modifier = Modifier
                        .padding(0.dp, 25.dp, 0.dp, 0.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFD8FF7E))
                        .padding(30.dp, 0.dp)
                        .height(50.dp)
                        .clickable {
                            //cousa que facer
                        },
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = "Plus",
                        modifier = Modifier
                            .padding(0.dp,0.dp,10.dp,0.dp)
                    )
                    Text(
                        text = "Añadir residuo",
                        fontSize = 14.sp
                    )
                }
            }

            //nota para albaran
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Nota para el albarán",
                    fontSize = 14.sp,
                    color = Color(0xC6FFFFFF)
                )

                //campo de texto (cambiar color pero é unha puta liada)
                var textoAlbaran by remember { mutableStateOf("") }
                BasicTextField(
                    value = textoAlbaran,
                    onValueChange = { textoAlbaran = it},
                    modifier = Modifier
                        .padding(0.dp, 10.dp, 0.dp, 0.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF292928))
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(10.dp),
                    textStyle = LocalTextStyle.current.copy(color = Color.White)
                )
            }

            //Observaciones
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Observaciones",
                    fontSize = 14.sp,
                    color = Color(0xC6FFFFFF)
                )

                //campo de texto (cambiar color pero é unha puta liada)
                var textoObservaciones by remember { mutableStateOf("") }
                BasicTextField(
                    value = textoObservaciones,
                    onValueChange = { textoObservaciones = it},
                    modifier = Modifier
                        .padding(0.dp, 10.dp, 0.dp, 0.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF292928))
                        .fillMaxWidth()
                        .height(150.dp),
                    textStyle = LocalTextStyle.current.copy(color = Color.White),
                )
            }

            //Fotografías
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Fotografías",
                    fontSize = 14.sp,
                    color = Color(0xC6FFFFFF)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    //añadir foto
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .padding(0.dp)
                            .background(Color(0xFFDCF2AC))
                            .fillMaxHeight()
                            .padding(0.dp, 5.dp)
                        ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.camera),
                            contentDescription = "New photo",
                            modifier = Modifier
                                .height(40.dp)
                                .aspectRatio(1f)
                                .padding(0.dp, 0.dp, 15.dp, 0.dp)
                        )
                        Text(
                            text = "Añadir fotografía"
                        )
                    }
                    //ver fotos
                    Box(
                        modifier = Modifier
                            .padding(0.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .fillMaxHeight()
                            .background(Color(0xFF7F7F7F))
                            .padding(15.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.gallery),
                            contentDescription = "Galeria",
                            modifier = Modifier
                                .height(40.dp)
                                .aspectRatio(1f)
                        )
                    }
                }
            }

            //Estado de regogida
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 40.dp, 0.dp, 30.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Estado",
                    fontSize = 14.sp,
                    color = Color(0xC6FFFFFF)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFF0625C))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.cancel),
                            contentDescription = "Cross",
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1f)
                                .padding(0.dp, 0.dp, 15.dp, 0.dp)
                        )
                        Text(
                            text = "No recogido",
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFF1F515D))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.person_check),
                            contentDescription = "Cross",
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1f)
                                .padding(0.dp, 0.dp, 15.dp, 0.dp)
                        )
                        Text(
                            text = "Completar",
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFD8FF7E))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.signature),
                        contentDescription = "Signature",
                        modifier = Modifier
                            .height(40.dp)
                            .aspectRatio(1f)
                            .padding(0.dp, 0.dp, 15.dp, 0.dp)
                    )
                    Text(
                        text = "Firmar",
                    )
                }
            }
        }
    }
}

@Composable
fun ItemInDestinationScreen(
    navController: NavController
) {
    var selectedBottomNavItem by remember {
        mutableIntStateOf(0)
    }

    var a by remember { mutableStateOf("") }


    Scaffold(
        containerColor = Color.Yellow, //para que o menu estea flotando
        topBar = {
            Row(
                Modifier
                    .background(Color(0xFF1D1D1D))
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                                contentDescription = "Atrás",
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .aspectRatio(1f),
                                tint = Color.White
                            )
                        }

                        Text(
                            text = "Talleres Juan Antornio SL...",
                            color = Color.White
                        )
                    }
                }
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF292928),
                modifier = Modifier
                    .background(Color(0xFF292928))
            ) {
                bottomNavItems.forEachIndexed { i, item ->
                    NavigationBarItem(
                        selected = i == selectedBottomNavItem,
                        onClick = {
                            selectedBottomNavItem = i
                            navController.navigate(item.route)
                        },
                        icon = {
                            Icon(
                                imageVector = item.unselectedIcon,
                                contentDescription = item.title,
                                tint = Color.White
                            )
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF1D1D1D))
                .padding(16.dp, 16.dp, 16.dp, 0.dp)
                .verticalScroll(rememberScrollState())
            ,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ){
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Información del residuo",
                    fontSize = 14.sp,
                    color = Color(0xC6FFFFFF)
                )
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF292928))
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(15.dp)
                        .clickable {
                            navController.navigate("ItemInDestinationScreen")
                        }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bidon_aceite),
                        contentDescription = "Oil",
                        modifier = Modifier
                            .fillMaxHeight(),
                        tint = Color(0xFF7F7F7F)
                    )
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(15.dp, 0.dp, 0.dp, 0.dp)
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = "Aceite usado",
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f),
                    ) {
                        Text(
                            text = "Peso",
                            fontSize = 14.sp,
                            color = Color(0xC6FFFFFF),
                            modifier = Modifier
                                .padding(10.dp,0.dp,0.dp,5.dp)
                        )
                        TextField(
                            value = a,
                            onValueChange = {
                                a=it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = Color(0xFF292928),
                                    shape = RoundedCornerShape(10.dp)
                                )
                            ,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            colors = CustomTextInputColors,
                            textStyle = TextStyle(color = Color.White),
                            trailingIcon = {
                                if (true){ //se ten metidas (kg,l...)
                                    IconButton(onClick = {  }) {
                                        Text(
                                            text = "kg",
                                            color = Color(0xC6FFFFFF)
                                        )
                                    }
                                }
                            }
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f),
                    ) {
                        Text(
                            text = "Bultos",
                            fontSize = 14.sp,
                            color = Color(0xC6FFFFFF),
                            modifier = Modifier
                                .padding(10.dp,0.dp,0.dp,5.dp)
                        )
                        TextField(
                            value = a,
                            onValueChange = {
                                a=it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = Color(0xFF292928),
                                    shape = RoundedCornerShape(10.dp)
                                )
                            ,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            colors = CustomTextInputColors,
                            textStyle = TextStyle(color = Color.White),
                            trailingIcon = {
                                if (true){ //se ten metidas (kg,l...)
                                    IconButton(onClick = {  }) {
                                        Text(
                                            text = "kg",
                                            color = Color(0xC6FFFFFF)
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f),
                    ) {
                        Text(
                            text = "Contenedor",
                            fontSize = 14.sp,
                            color = Color(0xC6FFFFFF),
                            modifier = Modifier
                                .padding(10.dp,0.dp,0.dp,5.dp)
                        )
                        var expanded by remember { mutableStateOf(false) }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = !expanded }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Bidon") },
                                onClick = {}
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f),
                    ) {
                        Text(
                            text = "Nº envases",
                            fontSize = 14.sp,
                            color = Color(0xC6FFFFFF),
                            modifier = Modifier
                                .padding(10.dp,0.dp,0.dp,5.dp)
                        )
                        TextField(
                            value = a,
                            onValueChange = {
                                a=it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = Color(0xFF292928),
                                    shape = RoundedCornerShape(10.dp)
                                )
                            ,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            colors = CustomTextInputColors,
                            textStyle = TextStyle(color = Color.White),
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var checked by remember { mutableStateOf(true) }

                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            checked = it
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color(0xFF39CB4B),
                            checkedTrackColor = Color(0xFFD8FF7E),
                            uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                            uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                        )
                    )
                    Text(
                        text = "Recogido",
                        color = Color.White
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(0.dp,40.dp,0.dp,20.dp)
                        .fillMaxWidth()
                        .height(90.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFD8FF7E))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.save),
                        contentDescription = "Signature",
                        modifier = Modifier
                            .height(40.dp)
                            .aspectRatio(1f)
                            .padding(0.dp, 0.dp, 15.dp, 0.dp)
                    )
                    Text(
                        text = "Guardar",
                    )
                }
            }
        }
    }
}

private val CustomTextInputColors: TextFieldColors
    @Composable
    get() = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = Color(0xFF292928),
        unfocusedContainerColor = Color(0xFF292928),
        cursorColor = Color.White,
        focusedLabelColor = Color.White,
        unfocusedLabelColor = Color.White,
        focusedBorderColor = Color.White,
        unfocusedBorderColor = Color.White,
        focusedLeadingIconColor = Color.White,
        unfocusedLeadingIconColor = Color.White,
        focusedTrailingIconColor = Color.White,
        unfocusedTrailingIconColor = Color.White,
        errorBorderColor = Color.White,
        errorTextColor = Color.White,
        errorLeadingIconColor = Color.White,
        errorTrailingIconColor = Color.White,
        errorLabelColor = Color.White,
        errorSupportingTextColor = Color(0xFFFF5252), // Typical error color (Red)
        focusedSupportingTextColor = Color.White.copy(alpha = 0.7f), // Slightly dimmed white
        unfocusedSupportingTextColor = Color.White.copy(alpha = 0.7f) // Slightly dimmed white
    )
