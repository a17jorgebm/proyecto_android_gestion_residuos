package com.example.proyectoresiduoscompose.presentation.ui.routeCollectionScreen

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoresiduoscompose.R
import com.example.proyectoresiduoscompose.app.host.bottomNavItems

@Composable
fun RouteCollectionScreen(
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
                                navController.navigate("EditResidueScreen")
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
                        .clickable {
                            navController.navigate("SignatureScreen")
                        }
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