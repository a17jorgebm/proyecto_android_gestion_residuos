package com.example.proyectoresiduoscompose.presentation.ui.routeInfoScreen

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoresiduoscompose.R
import com.example.proyectoresiduoscompose.app.host.bottomNavItems
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun RouteInfoScreen(
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
                bottomNavItems.forEachIndexed{ i, item ->
                    NavigationBarItem(
                        selected = false,
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
                            contentDescription = stringResource(id = R.string.your_truck),
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
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        //
                    }
            ) {
                val locations = listOf(
                    LatLng(42.88998894861685, -8.547087142329467), // Singapore
                    LatLng(42.88728026972888, -8.547663533679275), // Marina Bay Sands
                    LatLng(42.893471824451005, -8.547311583961507), // Gardens by the Bay
                    LatLng(42.86488845269943, -8.555121063424268), // Sentosa Island
                    LatLng(42.85321241482345, -8.671803089872805), // Orchard Road
                    LatLng(42.88610856246535, -8.528606240572675), // Singapore Zoo
                    LatLng(42.89912376837267, -8.547145319160602)  // Bukit Timah Nature Reserve
                )

                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(locations[0], 10f)
                }

                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    locations.forEachIndexed { index, location ->
                        Marker(
                            state = MarkerState(position = location),
                            title = "${stringResource(id = R.string.workshop)} ${index + 1}",
                            snippet = "${stringResource(id = R.string.workshop)} ${index + 1}"
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "7 ${stringResource(id = R.string.customers)}",
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
                            if (it!=6){ //in the last one it doesnt show the connecting line
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight() // 140+16+15 (heightContenedor+gap+padding)
                                        .width(3.dp)
                                        .offset(0.dp, 35.dp) //15+20
                                        .align(Alignment.TopCenter)
                                        .background(Color(0xFFD8FF7E))
                                )
                            }
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
                                    navController.navigate("RouteCollectionScreen")
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
                                    text = stringResource(id = R.string.view),
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
}