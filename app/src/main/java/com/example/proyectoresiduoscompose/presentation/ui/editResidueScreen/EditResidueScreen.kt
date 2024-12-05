package com.example.proyectoresiduoscompose.presentation.ui.editResidueScreen;

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoresiduoscompose.R
import com.example.proyectoresiduoscompose.app.host.bottomNavItems

@Composable
fun EditResidueScreen(
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